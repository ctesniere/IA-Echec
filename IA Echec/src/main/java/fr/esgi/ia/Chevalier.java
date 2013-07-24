package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe repr�sente le chevalier Chacun des joueurs commence avec deux
 * cavaliers, plac�s en b1 et g1 pour les blancs, et b8 et g8 pour les noirs. Le
 * d�placement du cavalier est original. Il se d�place en L, c�est-�-dire de
 * deux cases dans une direction puis d'une perpendiculairement. C'est la seule
 * pi�ce du jeu qui ne soit pas bloqu�e dans son d�placement par les autres
 * pi�ces. Cette particularit� le rend tr�s utile dans les positions ferm�es. Le
 * cavalier permet aussi de faire des fourchettes (menace de deux pi�ces � la
 * fois).
 * 
 * @author C�dric TESNIERE
 * @since 1
 */
public class Chevalier extends Piece {

	public Chevalier(boolean color) {
		super();
		setColor(color);

		if (isColor() == false)
			setValue(-300);
		else
			setValue(300);
	}

	/**
	 * Used when a chessboard must be cloned.
	 */
	@Override
	public Object clone() {
		Chevalier myClone = new Chevalier(isColor());
		myClone.setValPos(getValPos());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		myClone.setPosition(getX(), getY());
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

		int toX = 0, toY = 0;
		ArrayList<Move> moves = new ArrayList<Move>();

		for (int i = 0; i < 8; i++) {

			if (i == 0) {
				toX = getX() + 2;
				toY = getY() + 1;
			}
			if (i == 1) {
				toX = getX() + 2;
				toY = getY() - 1;
			}
			if (i == 2) {
				toX = getX() - 2;
				toY = getY() + 1;
			}
			if (i == 3) {
				toX = getX() - 2;
				toY = getY() - 1;
			}
			if (i == 4) {
				toX = getX() + 1;
				toY = getY() + 2;
			}
			if (i == 5) {
				toX = getX() - 1;
				toY = getY() + 2;
			}
			if (i == 6) {
				toX = getX() - 1;
				toY = getY() - 2;
			}
			if (i == 7) {
				toX = getX() + 1;
				toY = getY() - 2;
			}

			Move move = checkThis(toX, toY, chessboard);
			if (move != null)
				moves.add(move);

		}

		return moves;
	}

	/**
	 * Test le possible d�placement
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
}
