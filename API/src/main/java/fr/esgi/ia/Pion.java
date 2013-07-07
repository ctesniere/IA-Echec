package fr.esgi.ia;

import java.util.ArrayList;

/**
 * This class represent the Pawn piece.
 * 
 * @author CŽdric TESNIERE
 */
public class Pion extends Piece {

	/** Creates a new instance of Pawn from it's ID */
	public Pion(int _id) {

		super();

		setId(_id);
		setColour(Helper.IsEven(_id));

		if (isColour() == false)
			setValue(-100);
		else
			setValue(100);

		// Starting position
		String square = getStartingPosition(_id);
		setX(Helper.getXfromString(square));
		setY(Helper.getYfromString(square));

	}

	/**
	 * Used when a chessboard must be cloned.
	 */
	public Object clone() {

		Pion myClone = new Pion(this.getId());
		myClone.setInDanger(this.isInDanger());
		myClone.setEnemy(this.getEnemy());
		myClone.setMoved(this.isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(this.getValPos());
		return myClone;
	}

	/**
	 * The Pawn can move until it finds an enemy of an ally piece. He can eat in
	 * other positions. We give all possible moves, not the good ones.
	 * 
	 * @param _chessboard
	 *            The actual chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {

		int toX = getX();
		int toY = getY();

		ArrayList<Move> moves = new ArrayList<Move>();

		// Movement
		if (isColour())
			toY++;
		else
			toY--;

		Piece possiblePiece = _chessboard.getPiece(toX, toY);

		if (possiblePiece == null) {

			Move move = new Move(getX(), getY(), toX, toY, this.isColour());
			if (toY == 7 || toY == 0)
				move.SetPromo();
			if (move.isValid())
				moves.add(move);

			// Two square
			if (!(isMoved())) {
				if (isColour())
					toY++;
				else
					toY--;

				possiblePiece = _chessboard.getPiece(toX, toY);

				if (possiblePiece == null) {
					move = new Move(getX(), getY(), toX, toY, this.isColour());
					// move.SetCatturabile();
					if (move.isValid())
						moves.add(move);
				}

			}
		}

		// Can i ate?

		toX = getX() - 1;
		// Sx
		if (isColour())
			toY = getY() + 1;
		else
			toY = getY() - 1;

		possiblePiece = _chessboard.getPiece(toX, toY);

		if ((possiblePiece != null)
				&& (possiblePiece.isColour() != this.isColour())) {

			Move move = new Move(getX(), getY(), toX, toY, this.isColour());

			if (toY == 7 || toY == 0)
				move.SetPromo();
			if (move.isValid())
				moves.add(move);
		}

		toX = getX() + 1;

		if (isColour())
			toY = getY() + 1;
		else
			toY = getY() - 1;

		possiblePiece = _chessboard.getPieceMuov(toX, toY);

		if ((possiblePiece != null)
				&& (possiblePiece.isColour() != this.isColour())) {

			Move move = new Move(getX(), getY(), toX, toY, this.isColour());

			if (toY == 7 || toY == 0)
				move.SetPromo();
			if (move.isValid())
				moves.add(move);
		}

		return moves;
	}

	/*
	 * private void setChessValue() {
	 * 
	 * int r0=7; int r1=6; int r2=5; int r3=4; int r4=3; int r5=2; int r6=1; int
	 * r7=0; if (setColour(true)) { r0=0; r1=1; r2=2; r3=3; r4=4; r5=5; r6=6;
	 * r7=7; }
	 * 
	 * // Riga 0 for (int j=0; j<8; j++) valPos[r0][j] = 0;
	 * 
	 * // Riga 1 valPos[r1][0] = 0; valPos[r1][1] = 0; valPos[r1][2] = 0;
	 * valPos[r1][3] = -4; valPos[r1][4] = -4; valPos[r1][5] = 0; valPos[r1][6]
	 * = 0; valPos[r1][7] = 0;
	 * 
	 * // Riga 2 valPos[r2][0] = 1; valPos[r2][1] = 2; valPos[r2][2] = 3;
	 * valPos[r2][3] = 4; valPos[r2][4] = 4; valPos[r2][5] = 3; valPos[r2][6] =
	 * 2; valPos[r2][7] = 1;
	 * 
	 * // Riga 3 valPos[r3][0] = 2; valPos[r3][1] = 4; valPos[r3][2] = 6;
	 * valPos[r3][3] = 8; valPos[r3][4] = 8; valPos[r3][5] = 6; valPos[r3][6] =
	 * 4; valPos[r3][7] = 2;
	 * 
	 * // Riga 4 valPos[r4][0] = 3; valPos[r4][1] = 6; valPos[r4][2] = 9;
	 * valPos[r4][3] = 12; valPos[r4][4] = 12; valPos[r4][5] = 9; valPos[r4][6]
	 * = 6; valPos[r4][7] = 3;
	 * 
	 * // Riga 5 valPos[r5][0] = 4; valPos[r5][1] = 8; valPos[r5][2] = 12;
	 * valPos[r5][3] = 16; valPos[r5][4] = 16; valPos[r5][5] = 12; valPos[r5][6]
	 * = 8; valPos[r5][7] = 4;
	 * 
	 * // Riga 6 valPos[r6][0] = 5; valPos[r6][1] = 10; valPos[r6][2] = 15;
	 * valPos[r6][3] = 20; valPos[r6][4] = 20; valPos[r6][5] = 15; valPos[r6][6]
	 * = 10; valPos[r6][7] = 5;
	 * 
	 * // Riga 7 valPos[r7][0] = 35; valPos[r7][1] = 35; valPos[r7][2] = 35;
	 * valPos[r7][3] = 35; valPos[r7][4] = 35; valPos[r7][5] = 35; valPos[r7][6]
	 * = 35; valPos[r7][7] = 35; }
	 */

}
