package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente le roi. Un roi peut se déplacer d'une case dans
 * toutes les directions (horizontalement, verticalement, ou diagonalement),
 * mais il ne peut aller sur une case où il serait menacé par une pièce ennemie
 * (se mettre en prise, en échec). En conséquence, on ne verra jamais les deux
 * rois ennemis côte à côte. Comme toutes les autres pièces, il ne peut aller
 * sur une case déjà occupée par une pièce de son camp, et il prend en se
 * déplaçant sur la case occupée par une pièce ennemie.
 * 
 * @author Cédric TESNIERE
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
	 * Création d'une instance Roi
	 * 
	 * @param color
	 */
	public Roi(int id, boolean color) {
		super(id, color, 10000);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	/**
	 * Used when a chessboard must be cloned.
	 */
	@Override
	public Object clone() {
		Roi myClone = new Roi(getId(), isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
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

		int toX = -1, toY = -1;
		ArrayList<Move> moves = new ArrayList<Move>();

		String positionPiece = chessboard.getPositionPiece(this);
		int getX = Helper.getXfromString(positionPiece);
		int getY = Helper.getYfromString(positionPiece);

		for (int direction = 0; direction < 8; direction++) {

			if (direction == 0) {
				toX = getX + 1;
				toY = getY + 1;
			}
			if (direction == 1) {
				toX = getX - 1;
				toY = getY + 1;
			}
			if (direction == 2) {
				toX = getX - 1;
				toY = getY - 1;
			}
			if (direction == 3) {
				toX = getX + 1;
				toY = getY - 1;
			}
			if (direction == 4) {
				toX = getX + 1;
				toY = getY;
			}
			if (direction == 5) {
				toX = getX;
				toY = getY + 1;
			}
			if (direction == 6) {
				toX = getX - 1;
				toY = getY;
			}
			if (direction == 7) {
				toX = getX;
				toY = getY - 1;
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
