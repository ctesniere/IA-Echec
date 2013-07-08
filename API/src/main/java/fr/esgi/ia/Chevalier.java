package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe représente le chevalier
 * 
 * Chacun des joueurs commence avec deux cavaliers, placés en b1 et g1 pour les
 * blancs, et b8 et g8 pour les noirs. Le déplacement du cavalier est original.
 * Il se déplace en L, c’est-à-dire de deux cases dans une direction puis d'une
 * perpendiculairement. C'est la seule pièce du jeu qui ne soit pas bloquée dans
 * son déplacement par les autres pièces. Cette particularité le rend très utile
 * dans les positions fermées. Le cavalier permet aussi de faire des fourchettes
 * (menace de deux pièces à la fois).
 * 
 * @author Cédric TESNIERE
 */
public class Chevalier extends Piece {

	public Chevalier(boolean _color) {
		super();
		setColor(_color);

		if (isColor() == false)
			setValue(-300);
		else
			setValue(300);
	}

	/**
	 * Used when a chessboard must be cloned.
	 */
	public Object clone() {
		Chevalier myClone = new Chevalier(isColor());
		myClone.setValPos(this.getValPos());
		myClone.setInDanger(this.isInDanger());
		myClone.setEnemy(this.getEnemy());
		myClone.setMoved(this.isMoved());
		myClone.setPosition(getX(), getY());
		return myClone;
	}

	/**
	 * Donne tous les coups possibles, pas les bons.
	 * 
	 * @param _chessboard
	 *            Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {

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

			Move move = checkThis(toX, toY, _chessboard);
			if (move != null)
				moves.add(move);

		}

		return moves;
	}

	/**
	 * Test le possible déplacement
	 * 
	 * @param _toX
	 * @param _toY
	 * @param _chessboard
	 *            Actuel chessboard
	 * @return A Move or NULL.
	 */
	private Move checkThis(int _toX, int _toY, Chessboard _chessboard) {

		Piece destination = _chessboard.getPieceMouv(_toX, _toY);
		Move move;

		if (destination != null) {
			if (destination.isColor() != this.isColor()) {
				move = new Move(getX(), getY(), _toX, _toY, this.isColor());
				if (move.isValid())
					return move;
			}
		} else {
			move = new Move(getX(), getY(), _toX, _toY, this.isColor());
			if (move.isValid())
				return move;
		}

		return null;
	}
}