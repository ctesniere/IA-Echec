package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe repr�sente la tour.
 * 
 * La tour peut se d�placer horizontalement ou verticalement. Cette pi�ce est �
 * longue port�e, c'est-�-dire qu'elle peut �tre d�plac�e d'autant de cases
 * qu'on le souhaite, sans pouvoir sauter par dessus une autre pi�ce. Chaque
 * camp poss�de deux tours. Elles se positionnent sur les cases a1 et h1 pour
 * les blancs et a8 et h8 pour les noirs.
 * 
 * @author C�dric TESNIERE
 */
public class Tour extends Piece {

	/**
	 * Cr�ation d'une instance d'une tour
	 * 
	 * @param _color
	 */
	public Tour(boolean _color) {
		super();
		setColor(_color);

		if (isColor() == false)
			setValue(-500);
		else
			setValue(500);
	}

	/**
	 * Used when a chessboard must be cloned.
	 */
	@Override
	public Object clone() {
		Tour myClone = new Tour(isColor());
		myClone.setInDanger(this.isInDanger());
		myClone.setEnemy(this.getEnemy());
		myClone.setMoved(this.isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(this.getValPos());
		return myClone;
	}

	/**
	 * The Rook can move until it finds an enemy of an ally piece. We give all
	 * possible moves, not the good ones.
	 * 
	 * @param _chessboard
	 *            Actuel chessboard
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
					toY = getY();
				}
				if (direction == 1) {
					toX = getX();
					toY = getY() + length;
				}
				if (direction == 2) {
					toX = getX() - length;
					toY = getY();
				}
				if (direction == 3) {
					toX = getX();
					toY = getY() - length;
				}

				/*
				 * OLD CODE
				 */
				/*
				 * Move move = checkMove(toX, toY, chessboard); if (move !=
				 * null) { moves.add(move); } else { //If move is null, no more
				 * possible moves in this direction break; //Stop going in this
				 * direction }
				 */

				// TODO: Change checkThis to return a Boolean
				Move move = checkMove(toX, toY, _chessboard, moves);
				if (move == null) {
					// If move is null, no more possible moves in this direction
					break; // Stop going in this direction
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
	private Move checkMove(int _toX, int _toY, Chessboard _chessboard,
			ArrayList<Move> _moves) {

		Piece destination = _chessboard.getPieceMouv(_toX, _toY);
		Move move;

		if (destination != null) { // If is not empty
			if (destination.isColor() != this.isColor()) { // Another color
				move = new Move(getX(), getY(), _toX, _toY, this.isColor());
				if (move.isValid()) {
					_moves.add(move); // Add move
				}
			}
			// Mine or not, if there is a piece STOP
			return null; // You must stop
		} else {
			move = new Move(getX(), getY(), _toX, _toY, this.isColor());
			if (move.isValid()) {
				_moves.add(move); // Add move
			}
		}

		return move;
	}
}