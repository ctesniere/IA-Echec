package fr.esgi.ia;

import java.util.ArrayList;

/**
 * This class, used in the AlphaBeta process, store a chessboard, it's value and
 * other useful information.
 * 
 * @author C�dric TESNIERE
 */
public class ChessboardValue {

	private Chessboard actualChessboardClone = null; /* < The chessboard */
	private Move lastMove = null;
	private ArrayList<Move> moves;
	private int value;
	private boolean color;

	public ChessboardValue(Chessboard chessboard, Move move,
			ArrayList<Move> earlyMoves) {

		actualChessboardClone = (Chessboard) chessboard.clone();
		moves = new ArrayList<Move>();

		// If it's not the first time [<>null(=> <> form alpha or beta)]
		if (move != null) {
			color = move.isColour();
			// Do the move and set the validity
			if (!(actualChessboardClone.doMove(move))) {
				move.setValid(false);
			} else {
				lastMove = move;
				lastMove.setValid(true);

				// Let's copy the moves
				// TODO: Clone?
				if (earlyMoves != null) {
					for (Move thisMove : earlyMoves) {
						moves.add((Move) thisMove);
					}
				}

				// Add this move
				moves.add(move);
			}
		}

		// Find and save the value of the chessboard
		value = chessboardValue(color);
	}

	/**
	 * Get the better move on the list.
	 * 
	 * @return The best possible move
	 */
	public Move getBestMove() {
		if (getMoves().isEmpty()) {
			return null;
		} else {
			return getMoves().get(0);
		}
	}

	/**
	 * Return the min between this chessboard and the passed one.
	 * 
	 * @param elseVChessboard
	 *            The other chessboard
	 * @return The min between the two
	 */
	public ChessboardValue VSmin(ChessboardValue elseVChessboard) {

		if ((lastMove == null) && (elseVChessboard.lastMove == null)) {
			if (getValue() < elseVChessboard.getValue()) {
				return this;
			} else {
				return elseVChessboard;
			}
		}

		if (lastMove == null) {
			return elseVChessboard;
		}

		if (elseVChessboard.lastMove == null) {
			return this;
		}

		if (getValue() < elseVChessboard.getValue()) {
			return this;
		} else {
			return elseVChessboard;
		}
	}

	/**
	 * Return the max between this chessboard and the passed one.
	 * 
	 * @param elseVChessboard
	 *            The other chessboard
	 * @return The max between the two
	 */
	public ChessboardValue VSmax(ChessboardValue elseVChessboard) {

		if ((lastMove == null) && (elseVChessboard.lastMove == null)) {
			if (getValue() > elseVChessboard.getValue()) {
				return this;
			} else {
				return elseVChessboard;
			}
		}

		if (lastMove == null) {
			return elseVChessboard;
		}

		if (elseVChessboard.lastMove == null) {
			return this;
		}

		if (getValue() > elseVChessboard.getValue()) {
			return this;
		} else {
			return elseVChessboard;
		}
	}

	/**
     * 
     */
	public boolean isLastMoveValid() {
		if (lastMove != null) {
			return lastMove.isValid();
		} else {
			return false;
		}
	}

	/**
	 * Return the value of the chessboard for a color.
	 * 
	 * @param color
	 *            The color of the player
	 * @return The value of the chessboard
	 */
	public int chessboardValue(boolean color) {

		int val = 0;
		int temp;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				// Se la casella non è vuota
				if (getActualChessboard().getPieceMuov(x, y) != null) {
					Piece pezzo = getActualChessboard().getPieceMuov(x, y);
					// Se il pezzo non è in pericolo ed è del mio colore
					if ((!pezzo.isInDanger()) && (pezzo.isColour() == color)) {
						// e si è mosso
						if (pezzo.isMoved()) {
							// Valutazione normale
							temp = pezzo.getValue() + pezzo.getPositionValue();
							val = val + temp;
						}
						// Se non si è mosso
						else {
							// Se è nero
							if (pezzo.isColour() == false) {
								// Se sono le mie torri o il re
								if ((pezzo.getId() == 31)
										|| (pezzo.getId() == 27)
										|| (pezzo.getId() == 25)) {
									// Valutazione normale
									temp = pezzo.getValue()
											+ pezzo.getPositionValue();
									val = val + temp;
								}
								// Se è un altro pezzo qualsiasi
								else {
									// Dai un vantaggio al bianco di 10
									temp = pezzo.getValue()
											+ pezzo.getPositionValue();
									val = val + temp + 10;
								}
							}
							// Se è il bianco
							else {
								// Se sono le mie torri o il re
								if ((pezzo.getId() == 32)
										|| (pezzo.getId() == 28)
										|| (pezzo.getId() == 26)) {
									// Valutazione normale
									temp = pezzo.getValue()
											+ pezzo.getPositionValue();
									val = val + temp;
								}
								// Se è un altro pezzo qualsiasi
								else {
									// Dai un vantaggio al nero di 10
									temp = pezzo.getValue()
											+ pezzo.getPositionValue();
									val = val + temp - 10;
								}
							}
						}
					}
					// Se il pezzo è in pericolo e non è del mio colore
					if ((pezzo.isInDanger()) && (pezzo.isColour() != color)) {
						// Dai un vantaggio del 15% del valore del pezzo
						temp = (int) pezzo.getValue() * 15 / 100;
						val = val - temp;
					}
					// Se il pezzo non è del mio colore
					if (pezzo.isColour() != color) {
						// Valutazione normale
						temp = pezzo.getValue() + pezzo.getPositionValue();
						val = val + temp;
					}
				}
			}
		}
		// Valuta la mobilità
		temp = getActualChessboard().getnWhiteMoves()
				- getActualChessboard().getnBlackMoves();
		val += temp * 2;
		return val;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	private void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setActualChessboard(Chessboard actualChessboard) {
		this.actualChessboardClone = actualChessboard;
	}

	public Chessboard getActualChessboard() {
		return actualChessboardClone;
	}

}