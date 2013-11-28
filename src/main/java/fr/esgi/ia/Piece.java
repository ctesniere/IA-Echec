package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Generic Piece
 * 
 * @author Cédric TESNIERE
 */
abstract public class Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	/**
	 * Identifiant de la piece, permet d'identifier deux pieces identique comme
	 * des Tours
	 */
	private int id;

	/**
	 * Si la valeur des noir est négative, positive pour les blancs
	 */
	private int value;

	private int enemy;

	private boolean color, inDanger, moved;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Piece() {
		setInDanger(false); // No one is in danger at the start
		setMoved(false);
		setEnemy(0);
	}

	public Piece(int id, boolean color, int value) {
		this();

		setId(id);
		setColor(color);

		if (isColor() == false)
			setValue(value * (-1));
		else
			setValue(value);

	}

	// =========================================================================
	// METHODS
	// =========================================================================

	public void noMoreInDanger() {
		setEnemy(getEnemy() - 1);
		if (getEnemy() == 0)
			setInDanger(false);
	}

	public void inDanger() {
		setEnemy(getEnemy() + 1);
		setInDanger(true);
	}

	/**
	 * Direction du mouvement
	 * 
	 * @return Retourne -1 ou 1 selon la couleur de la piece
	 */
	public int directionMovementY() {
		if (Helper.isColorWhite(isColor()))
			return 1;
		else
			return -1;
	}

	/**
	 * Test le possible mouvement, si il est correcte alors on retourne un objet
	 * 'Move' sinon on retourne null
	 * 
	 * @param toX Coordonnée X de la cible
	 * @param toY Coordonnée Y de la cible
	 * @param diagonalAttackPion Ative les attaque en diagonale pour les pions
	 * @param chessboard Actuel chessboard
	 * @return A Move or NULL.
	 */
	public Move checkThis(int toX, int toY, Chessboard chessboard, boolean diagonalAttackPion,
			boolean inDanger, Boolean color) {

		Piece destination = chessboard.getPiece(toX, toY);

		String positionPiece = chessboard.getPositionPiece(this);
		int getX = Helper.getXfromString(positionPiece);
		int getY = Helper.getYfromString(positionPiece);

		// Création du mouvement
		Move move = new Move(this.getClass().getSimpleName(), getX, getY, toX, toY, isColor());

		// Si c'est une pièce enemie ou si la case est vide
		boolean validationMouv = false;
		if (destination == null) {
			if (!diagonalAttackPion)
				validationMouv = true;
		} else {
			if (destination.isColor() != isColor()
					&& (!this.getClass().equals(Pion.class) || diagonalAttackPion)) {
				validationMouv = true;
				move.setAttack(true); // Mouvement d'attaque
			}
		}

		if (validationMouv) {
			chessboard.generateAllPossibleMoves(!color);
		}

		// Si le mouvement est valide, on le retourne
		if (move.checkValidity() && validationMouv)
			return move;
		else
			return null;
	}

	/**
	 * @see Piece#checkThis(int, int, Chessboard, boolean, boolean, Boolean)
	 */
	public Move checkThis(int toX, int toY, Chessboard chessboard, boolean diagonalAttackPion) {
		return checkThis(toX, toY, chessboard, false, true, null);
	}

	/**
	 * @see Piece#checkThis(int, int, Chessboard, boolean, boolean, Boolean)
	 */
	public Move checkThis(int toX, int toY, Chessboard chessboard) {
		return checkThis(toX, toY, chessboard, false, true, null);
	}

	/**
	 * Cette fonction doit être mise en oeuvre dans chaque sous-classe.
	 */
	abstract public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard);

	public boolean pieceInDanger(int toX, int toY, Chessboard chessboard) {
		ArrayList<Move> moves = new ArrayList<Move>();

		String positionPiece = chessboard.getPositionPiece(this);
		int getX = Helper.getXfromString(positionPiece);
		int getY = Helper.getYfromString(positionPiece);

		for (int i = 0; i < 8; i++) {
			switch (i) {
			// Déplacement d'un cavalier
				case 0:
					toX = getX + 2;
					toY = getY + 1;
					break;
				case 1:
					toX = getX + 2;
					toY = getY - 1;
					break;
				case 2:
					toX = getX - 2;
					toY = getY + 1;
					break;
				case 3:
					toX = getX - 2;
					toY = getY - 1;
					break;
				case 4:
					toX = getX + 1;
					toY = getY + 2;
					break;
				case 5:
					toX = getX - 1;
					toY = getY + 2;
					break;
				case 6:
					toX = getX - 1;
					toY = getY + 2;
					break;
				case 7:
					toX = getX - 1;
					toY = getY + 2;
					break;

				default:
					break;
			}

			if (i == 0) {

			}
			if (i == 1) {
			}
			if (i == 2) {
			}
			if (i == 3) {
			}
			if (i == 4) {
			}
			if (i == 5) {
			}
			if (i == 6) {
				toX = getX - 1;
				toY = getY - 2;
			}
			if (i == 7) {
				toX = getX + 1;
				toY = getY - 2;
			}

			Move move = checkThis(toX, toY, chessboard);

			if (move != null)
				moves.add(move);

		}
		return false;
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	/**
	 * Cette fonction doit être mise en oeuvre dans chaque sous-classe.
	 */
	@Override
	public Object clone() {
		return new Object();
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isMoved() {
		return moved;
	}

	protected void setInDanger(boolean inDanger) {
		this.inDanger = inDanger;
	}

	public boolean isInDanger() {
		return inDanger;
	}

	protected boolean setColor(boolean color) {
		this.color = color;
		return color;
	}

	public boolean isColor() {
		return color;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	protected void setEnemy(int enemy) {
		this.enemy = enemy;
	}

	protected int getEnemy() {
		return enemy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
