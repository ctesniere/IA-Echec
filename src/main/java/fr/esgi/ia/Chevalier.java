package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe représente le chevalier Chacun des joueurs commence avec deux
 * cavaliers, placés en b1 et g1 pour les blancs, et b8 et g8 pour les noirs. Le
 * déplacement du cavalier est original. Il se déplace en L, c'est-à-dire de
 * deux cases dans une direction puis d'une perpendiculairement. C'est la seule
 * pièce du jeu qui ne soit pas bloquée dans son déplacement par les autres
 * pièces. Cette particularié le rend très utile dans les positions fermées. Le
 * cavalier permet aussi de faire des fourchettes (menace de deux pièces à la
 * fois).
 * 
 * @author Cédric TESNIERE
 * @since 1
 */
public class Chevalier extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Chevalier(int id, boolean color) {
		super(id, color, 320);
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
		final Chevalier myClone = new Chevalier(getId(), isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		return myClone;
	}

	/**
	 * Donne tous les coups possibles, pas les bons.
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

		for (int i = 0; i < 8; i++) {

			if (i == 0) {
				toX = getX + 2;
				toY = getY + 1;
			}
			if (i == 1) {
				toX = getX + 2;
				toY = getY - 1;
			}
			if (i == 2) {
				toX = getX - 2;
				toY = getY + 1;
			}
			if (i == 3) {
				toX = getX - 2;
				toY = getY - 1;
			}
			if (i == 4) {
				toX = getX + 1;
				toY = getY + 2;
			}
			if (i == 5) {
				toX = getX - 1;
				toY = getY + 2;
			}
			if (i == 6) {
				toX = getX - 1;
				toY = getY - 2;
			}
			if (i == 7) {
				toX = getX + 1;
				toY = getY - 2;
			}

			final Move move = checkThis(toX, toY, chessboard);

			if (move != null) {
				moves.add(move);
			}

		}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
