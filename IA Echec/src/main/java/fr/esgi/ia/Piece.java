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
	 * Si la valeur des noir est négative, positive pour les blancs
	 */
	private int value;

	/**
	 * Nom de la piece
	 */
	private String name;

	private int y, x, enemy;

	private boolean color, inDanger, moved;

	public int[][] valPos = new int[8][8];

	/** < Array for the position value */

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Piece() {
		setInDanger(false);
		setInDanger(false); // No one is in danger at the start
		setMoved(false);
		setEnemy(0);
		setName(this.getClass().getSimpleName());
	}

	public Piece(boolean color, int value) {
		this();

		setColor(color);

		if (isColor() == false)
			setValue(value * (-1));
		else
			setValue(value);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}

	public void noMoreInDanger() {
		setEnemy(getEnemy() - 1);
		if (getEnemy() == 0)
			setInDanger(false);
	}

	public void inDanger() {
		setEnemy(getEnemy() + 1);
		setInDanger(true);
	}

	public int getPositionValue() {
		if (isColorWhite())
			return valPos[y][x];
		else
			return -(valPos[y][x]);
	}

	public boolean isColorWhite() {
		if(Algorithm.isWhite() == isColor())
			return Algorithm.isWhite();
		else
			return Algorithm.isBlack();
	}
	
	/**
	 * Direction du mouvement
	 * 
	 * @return Retourne -1 ou 1 selon la couleur de la piece
	 */
	public int directionMovementY() {
		if (isColorWhite())
			return 1;
		else
			return -1;
	}

	/**
	 * Test le possible mouvement, si il est correcte alors on retourne un objet
	 * 'Move' sinon on retourne null
	 * 
	 * @param toX
	 * @param toY
	 * @param chessboard Actuel chessboard
	 * @return A Move or NULL.
	 */
	public Move checkThis(int toX, int toY, Chessboard chessboard) {

		Piece destination = chessboard.getPiece(toX, toY);

		// Si c'est une pièce enemie ou si la case est vide
		if (destination == null || destination.isColor() != isColor()) {
			Move move = new Move(getX(), getY(), toX, toY, isColor());

			// Si le mouvement est valide, on le retourne
			if (move.checkValidity())
				return move;
		}

		return null;
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

	protected void setX(int x) {
		this.x = x;
	}

	protected int getX() {
		return x;
	}

	protected void setY(int y) {
		this.y = y;
	}

	protected int getY() {
		return y;
	}

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

	protected void setValPos(int[][] valPos) {
		this.valPos = valPos;
	}

	protected int[][] getValPos() {
		return valPos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
