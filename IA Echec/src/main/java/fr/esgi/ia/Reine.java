package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe repr�sente la Reine. La dame est une pi�ce � longue port�e,
 * capable de se mouvoir en ligne droite, verticalement, horizontalement, et
 * diagonalement, sur un nombre quelconque de cases inoccup�es comme le montre
 * le diagramme sur la gauche, combinant ainsi le d�placement de la tour et
 * celui du fou. Comme pour les autres pi�ces du jeu d'�checs (except� pour le
 * pion lors de la prise en passant), la reine capture en occupant la case
 * occup�e par une pi�ce adverse. Ordinairement, la dame est l�g�rement plus
 * puissante qu'une tour et un fou associ�s, alors qu'elle est l�g�rement moins
 * puissante que deux tours. Elle vaut l'�quivalent de neuf � dix pions (cette
 * valeur n'est qu'une estimation de l'importance relative de la dame par
 * rapport aux autres pi�ces, ce n'est pas un �l�ment du jeu). Puisque la dame a
 * plus de valeur qu'aucune autre pi�ce, il est presque toujours d�favorable
 * d'�changer la dame contre une autre pi�ce que la dame adverse.
 * 
 * @author C�dric TESNIERE
 * @since 1
 */
public class Reine extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	/**
	 * Cr�ation d'une instance d'une reine
	 * 
	 * @param color
	 */
	public Reine(boolean color) {
		super();
		setColor(color);

		if (isColor() == false)
			setValue(-900);
		else
			setValue(900);
	}

	/**
	 * Used when a chessboard must be cloned.
	 */
	@Override
	public Object clone() {
		Reine myClone = new Reine(isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(getValPos());
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

		int toX = 0, toY = 0;
		ArrayList<Move> moves = new ArrayList<Move>();

		// 8 direction
		for (int direction = 0; direction < 8; direction++)
			// Max 8 moves
			for (int length = 1; length < 9; length++) {

				if (direction == 0) {
					toX = getX() + length;
					toY = getY() + length;
				}
				if (direction == 1) {
					toX = getX() - length;
					toY = getY() + length;
				}
				if (direction == 2) {
					toX = getX() - length;
					toY = getY() - length;
				}
				if (direction == 3) {
					toX = getX() + length;
					toY = getY() - length;
				}
				if (direction == 4) {
					toX = getX() + length;
					toY = getY();
				}
				if (direction == 5) {
					toX = getX();
					toY = getY() + length;
				}
				if (direction == 6) {
					toX = getX() - length;
					toY = getY();
				}
				if (direction == 7) {
					toX = getX();
					toY = getY() - length;
				}

				// TODO: Change checkThis to return a Boolean
				Move mossa = checkThis(toX, toY, chessboard, moves);
				if (mossa == null)
					break;
			}

		return moves;
	}

	/**
	 * Test the possible move.
	 * 
	 * @param toX
	 * @param toY
	 * @param chessboard Actuel chessboard
	 * @return A Move or NULL.
	 */
	private Move checkThis(int toX, int toY, Chessboard chessboard, ArrayList<Move> moves) {

		Piece destination = chessboard.getPieceMouv(toX, toY);
		Move move;

		if (destination != null) {
			if (destination.isColor() != isColor()) {
				move = new Move(getX(), getY(), toX, toY, isColor());
				if (move.isValid())
					moves.add(move);
			}
			return null;
		} else {
			move = new Move(getX(), getY(), toX, toY, isColor());
			if (move.isValid())
				moves.add(move);
		}
		return move;
	}
}
