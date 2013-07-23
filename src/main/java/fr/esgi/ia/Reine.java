package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe représente la Reine. La dame est une pièce à longue portée,
 * capable de se mouvoir en ligne droite, verticalement, horizontalement, et
 * diagonalement, sur un nombre quelconque de cases inoccupées comme le montre
 * le diagramme sur la gauche, combinant ainsi le déplacement de la tour et
 * celui du fou. Comme pour les autres pièces du jeu d'échecs (excepté pour le
 * pion lors de la prise en passant), la reine capture en occupant la case
 * occupée par une pièce adverse. Ordinairement, la dame est légèrement plus
 * puissante qu'une tour et un fou associés, alors qu'elle est légèrement moins
 * puissante que deux tours. Elle vaut l'équivalent de neuf à dix pions (cette
 * valeur n'est qu'une estimation de l'importance relative de la dame par
 * rapport aux autres pièces, ce n'est pas un élément du jeu). Puisque la dame a
 * plus de valeur qu'aucune autre pièce, il est presque toujours défavorable
 * d'échanger la dame contre une autre pièce que la dame adverse.
 * 
 * @author Cédric TESNIERE
 * @since 1
 */
public class Reine extends Piece {

	/**
	 * Création d'une instance d'une reine
	 * 
	 * @param _color
	 */
	public Reine(boolean _color) {
		super();
		setColor(_color);

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
	 * @param _chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {

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
				Move mossa = checkThis(toX, toY, _chessboard, moves);
				if (mossa == null)
					break;
			}

		return moves;
	}

	/**
	 * Test the possible move.
	 * 
	 * @param _toX
	 * @param _toY
	 * @param _chessboard Actuel chessboard
	 * @return A Move or NULL.
	 */
	private Move checkThis(int _toX, int _toY, Chessboard _chessboard, ArrayList<Move> _moves) {

		Piece destination = _chessboard.getPieceMouv(_toX, _toY);
		Move move;

		if (destination != null) {
			if (destination.isColor() != isColor()) {
				move = new Move(getX(), getY(), _toX, _toY, isColor());
				if (move.isValid())
					_moves.add(move);
			}
			return null;
		} else {
			move = new Move(getX(), getY(), _toX, _toY, isColor());
			if (move.isValid())
				_moves.add(move);
		}
		return move;
	}
}
