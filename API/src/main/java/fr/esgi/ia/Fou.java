package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente le fou.
 * 
 * Cette pièce est à longue portée, c’est-à-dire qu'elle peut être déplacée
 * d'autant de cases qu'on le souhaite, sans pouvoir sauter par dessus une autre
 * pièce. Il ne peut changer de couleur de case durant la partie et ne balaie
 * donc que la moitié de l'échiquier. À l'instar du cavalier, le fou est une
 * pièce mineure. En général, on lui attribue la même valeur que le cavalier, la
 * différence dépendant de la position sur l'échiquier.
 * 
 * @author Cédric TESNIERE
 */
public class Fou extends Piece {

	/**
	 * Création d'une instance fou
	 * 
	 * @param _color
	 */
	public Fou(boolean _color) {
		super();
		this.setColor(_color);

		if (isColor() == false)
			this.setValue(-325);
		else
			this.setValue(325);
	}

	@Override
	public Object clone() {
		Fou myClone = new Fou(isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(getValPos());
		return myClone;
	}

	/**
	 * The Bishop can move until it finds an enemy of an ally piece. We give all
	 * possible moves, not the good ones.
	 * 
	 * @param _chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {

		int toX = 0, toY = 0;
		ArrayList<Move> moves = new ArrayList<Move>();

		// 4 direction
		for (int direction = 0; direction < 4; direction++) {

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

				Move move = checkThis(toX, toY, _chessboard, moves);
				if (move == null) {
					break;
				}
			}
		}

		return moves;
	}

	/**
	 * Test the possible move.
	 * 
	 * @param _toX
	 * @param _toY
	 * @param _chessboard
	 *            Actuel chessboard
	 * @return A Move or NULL.
	 */
	private Move checkThis(int _toX, int _toY, Chessboard _chessboard,
			ArrayList<Move> _moves) {

		Piece destination = _chessboard.getPieceMouv(_toX, _toY);
		Move move;

		if (destination != null) {
			if (destination.isColor() != this.isColor()) {
				move = new Move(getX(), getY(), _toX, _toY, this.isColor());
				if (move.isValid()) {
					_moves.add(move);
				}
			}
			return null;
		} else {
			move = new Move(getX(), getY(), _toX, _toY, this.isColor());
			if (move.isValid()) {
				_moves.add(move);
			}
		}

		return move;
	}
}