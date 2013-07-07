package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Generic Piece
 * 
 * @author CŽdric TESNIERE
 */
abstract public class Piece {

	/**
	 * < If black the value is negative, positive otherwise
	 */
	private int value;

	private int id, y, x, enemy;

	private boolean colour, inDanger, moved;

	public int[][] valPos = new int[8][8];

	/** < Array for the position value */

	Piece() {
		inDanger = false; // No one is in danger at the start
		moved = false;
		setEnemy(0);
	}

	public String getStartingPosition(int ID) {
		return Chessboard.startingPosition(ID);
	}

	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}

	public void noMoreInDanger() {
		setEnemy(getEnemy() - 1);
		if (getEnemy() == 0) {
			inDanger = false;
		}
	}

	public void inDanger() {
		setEnemy(getEnemy() + 1);
		inDanger = true;
	}

	public int getPositionValue() {
		if (isColour()) {
			return valPos[y][x];
		} else {
			return -(valPos[y][x]);
		}
	}

	/**
	 * This function needs to be implemented in every subclass.
	 */
	abstract public ArrayList<Move> generateMovesForThisPiece(
			Chessboard chessboard);

	/**
	 * This function needs to be implemented in every subclass.
	 */
	@Override
	public Object clone() {
		return new Object();
	}

	// GETTER AND SETTER

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

	protected void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	protected boolean setColour(boolean colour) {
		this.colour = colour;
		return colour;
	}

	public boolean isColour() {
		return colour;
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

}
