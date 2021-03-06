package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe représente la tour. La tour peut se déplacer horizontalement ou
 * verticalement. Cette pièce est à longue portée, c'est-à-dire qu'elle peut
 * être déplacée d'autant de cases qu'on le souhaite, sans pouvoir sauter par
 * dessus une autre pièce. Chaque camp possède deux tours. Elles se positionnent
 * sur les cases a1 et h1 pour les blancs et a8 et h8 pour les noirs.
 * 
 * @author Cédric TESNIERE
 */
public class Tour extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Création d'une instance d'une tour
	 * 
	 * @param color
	 */
	public Tour(int id, boolean color) {
		super(id, color, 510);
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
		final Tour myClone = new Tour(getId(), isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		return myClone;
	}

	/**
	 * The Rook can move until it finds an enemy of an ally piece. We give all
	 * possible moves, not the good ones.
	 * 
	 * @param chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {

		int toX = -1, toY = -1;
		final ArrayList<Move> moves = new ArrayList<Move>();

		final String positionPiece = chessboard.getPositionPiece(this);
		final int getX = Helper.getXfromString(positionPiece);
		final int getY = Helper.getYfromString(positionPiece);

		// 4 direction
		for (int direction = 0; direction < 4; direction++)
			// Max 8 moves
		{
			for (int length = 1; length < 9; length++) {

				if (direction == 0) {
					toX = getX + length;
					toY = getY;
				}
				if (direction == 1) {
					toX = getX;
					toY = getY + length;
				}
				if (direction == 2) {
					toX = getX - length;
					toY = getY;
				}
				if (direction == 3) {
					toX = getX;
					toY = getY - length;
				}

				final Move move = checkThis(toX, toY, chessboard);

				// Si déplacement est nul, plus possible de ce déplacer dans
				// cette direction pour la tour
				if (move != null) {
					moves.add(move);
					if (move.isAttack()) {
						break;
					}
				} else {
					break;
				}
			}
		}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

}
