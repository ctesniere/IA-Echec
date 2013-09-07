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
	public Move checkThis(int toX, int toY, Chessboard chessboard, boolean diagonalAttackPion) {

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
			if(destination.isColor() != isColor() && (!this.getClass().equals(Pion.class) || diagonalAttackPion)) {
				validationMouv = true;
				move.setAttack(true); // Mouvement d'attaque
			}
		}

		// Si le mouvement est valide, on le retourne
		if (move.checkValidity() && validationMouv)
			return move;
		else
			return null;
	}

	/**
	 * @see Piece#checkThis(int, int, Chessboard, boolean, boolean)
	 */
	public Move checkThis(int toX, int toY, Chessboard chessboard) {
		return checkThis(toX, toY, chessboard, false);
	}

	/**
	 * Cette fonction doit être mise en oeuvre dans chaque sous-classe.
	 */
	abstract public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard);

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
