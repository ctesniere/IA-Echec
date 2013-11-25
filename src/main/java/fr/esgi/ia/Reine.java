package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe représente la Reine. La dame est une pièce à longue portée,
 * capable de se mouvoir en ligne droite, verticalement, horizontalement, et
 * diagonalement, sur un nombre quelconque de cases inoccupées comme le montre
 * le diagramme sur la gauche, combinant ainsi le déplacement de la tour et
 * celui du fou. Comme pour les autres pièces du jeu d'échecs (excepté pour le
 * pion lors de la prise en passant), la reine capture en occupant la case
 * occupée par une pièce adverse. Ordinairement, la dame est légerement plus
 * puissante qu'une tour et un fou associés, alors qu'elle est légerement moins
 * puissante que deux tours. Elle vaut l'équivalent de neuf à dix pions (cette
 * valeur n'est qu'une estimation de l'importance relative de la dame par
 * rapport aux autres pièces, ce n'est pas un élement du jeu). Puisque la dame a
 * plus de valeur qu'aucune autre pièce, il est presque toujours défavorable
 * d'échanger la dame contre une autre pèce que la dame adverse.
 * 
 * @author Cédric TESNIERE
 * @since 1
 */
public class Reine extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Création d'une instance d'une reine
	 * 
	 * @param color
	 */
	public Reine(int id, boolean color) {
		super(id, color, 880);
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
		Reine myClone = new Reine(getId(), isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		return myClone;
	}

	/**
	 * The Queen can move until it finds an enemy of an ally piece. We give all
	 * possible moves, not the good ones.
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

		// 8 direction
		for (int direction = 0; direction < 8; direction++)
			// Max 8 moves
			for (int length = 1; length < 9; length++) {

				if (direction == 0) {
					toX = getX + length;
					toY = getY + length;
				}
				if (direction == 1) {
					toX = getX - length;
					toY = getY + length;
				}
				if (direction == 2) {
					toX = getX - length;
					toY = getY - length;
				}
				if (direction == 3) {
					toX = getX + length;
					toY = getY - length;
				}
				if (direction == 4) {
					toX = getX + length;
					toY = getY;
				}
				if (direction == 5) {
					toX = getX;
					toY = getY + length;
				}
				if (direction == 6) {
					toX = getX - length;
					toY = getY;
				}
				if (direction == 7) {
					toX = getX;
					toY = getY - length;
				}

				Move move = checkThis(toX, toY, chessboard);

				// Si déplacement est nul, plus possible de ce déplacer dans
				// cette direction pour la reine
				if (move != null) {
					moves.add(move);
					if (move.isAttack())
						break;
				} else
					break;
			}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
