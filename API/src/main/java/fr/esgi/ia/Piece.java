package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Generic Piece
 * 
 * @author Cédric TESNIERE
 */
abstract public class Piece {

	/**
	 * < If black the value is negative, positive otherwise
	 */
	private int value;

	private int y, x, enemy;

	private boolean color, inDanger, moved;

	public int[][] valPos = new int[8][8];

	/** < Array for the position value */

	public Piece() {
		inDanger = false; // No one is in danger at the start
		moved = false;
		setEnemy(0);
	}

	public Piece(int _id) {

	}

	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}

	public void noMoreInDanger() {
		setEnemy(getEnemy() - 1);
		if (getEnemy() == 0) inDanger = false;
	}

	public void inDanger() {
		setEnemy(getEnemy() + 1);
		inDanger = true;
	}

	public int getPositionValue() {
		if (isColor()) return valPos[y][x];
		else return -(valPos[y][x]);
	}

	/**
	 * Cette fonction doit être mise en œuvre dans chaque sous-classe.
	 */
	abstract public ArrayList<Move> generateMovesForThisPiece(
			Chessboard chessboard);

	/**
	 * Cette fonction doit être mise en œuvre dans chaque sous-classe.
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

}
