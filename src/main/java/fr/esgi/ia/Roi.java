package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente le roi. Un roi peut se d�placer d'une case dans
 * toutes les directions (horizontalement, verticalement, ou diagonalement),
 * mais il ne peut aller sur une case o� il serait menac� par une pi�ce ennemie
 * (se mettre en prise, en �chec). En cons�quence, on ne verra jamais les deux
 * rois ennemis c�te � c�te. Comme toutes les autres pi�ces, il ne peut aller
 * sur une case d�j� occup�e par une pi�ce de son camp, et il prend en se
 * d�pla�ant sur la case occup�e par une pi�ce ennemie.
 * 
 * @author C�dric TESNIERE
 * @since 1
 */
public class Roi extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Cr�ation d'une instance Roi
	 * 
	 * @param color
	 */
	public Roi(boolean color) {
		super();
		setColor(color);

		if (isColor() == false)
			setValue(-200);
		else
			setValue(200);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * Test the possible move.
	 * 
	 * @param toX
	 * @param toY
	 * @param chessboard Actuel chessboard
	 * @return A Move or NULL.
	 */
	private Move checkThis(int toX, int toY, Chessboard chessboard) {

		Piece destination = chessboard.getPieceMouv(toX, toY);
		Move move;

		if (destination != null) {
			if (destination.isColor() != isColor()) {
				move = new Move(getX(), getY(), toX, toY, isColor());
				if (move.isValid())
					return move;
			}
		} else {
			move = new Move(getX(), getY(), toX, toY, isColor());
			if (move.isValid())
				return move;
		}

		return null;
	}

	/**
	 * Used when a chessboard must be cloned.
	 */
	@Override
	public Object clone() {
		Roi myClone = new Roi(isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(getValPos());
		return myClone;
	}

	/**
	 * We give all possible moves, not the good ones.
	 * 
	 * @param chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {

		int toX = 0, toY = 0;
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int i2 = 0; i2 < 10; i2++) {

			if (i2 == 0) {
				toX = getX() + 1;
				toY = getY() + 1;
			}
			if (i2 == 1) {
				toX = getX() - 1;
				toY = getY() + 1;
			}
			if (i2 == 2) {
				toX = getX() - 1;
				toY = getY() - 1;
			}
			if (i2 == 3) {
				toX = getX() + 1;
				toY = getY() - 1;
			}
			if (i2 == 4) {
				toX = getX() + 1;
				toY = getY();
			}
			if (i2 == 5) {
				toX = getX();
				toY = getY() + 1;
			}
			if (i2 == 6) {
				toX = getX() - 1;
				toY = getY();
			}
			if (i2 == 7) {
				toX = getX();
				toY = getY() - 1;
			}

			Move move = checkThis(toX, toY, chessboard);
			if (move != null)
				moves.add(move);

		}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
