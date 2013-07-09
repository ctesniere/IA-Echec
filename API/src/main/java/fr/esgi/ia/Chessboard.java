package fr.esgi.ia;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a chessboard.
 * 
 * @author CŽdric TESNIERE
 */
public class Chessboard implements Cloneable {

	// Number of possible moves. Used to calculate mobility.
	private int nWhiteMoves;
	private int nBlackMoves;

	private Piece[][] chessboard;

	private List<Piece> blacks;
	private List<Piece> whites;

	public Chessboard() {

		nWhiteMoves = 0;
		nBlackMoves = 0;

		this.setWhites(new ArrayList<Piece>());
		this.setBlacks(new ArrayList<Piece>());

		chessboard = new Piece[8][8];

		// Black
		chessboard[7][0] = new Tour(Algorithm.isBlack());
		chessboard[7][1] = new Chevalier(Algorithm.isBlack());
		chessboard[7][2] = new Fou(Algorithm.isBlack());

		chessboard[7][3] = new Reine(Algorithm.isBlack());
		chessboard[7][4] = new Roi(Algorithm.isBlack());

		chessboard[7][5] = new Fou(Algorithm.isBlack());
		chessboard[7][6] = new Chevalier(Algorithm.isBlack());
		chessboard[7][7] = new Tour(Algorithm.isBlack());

		chessboard[6][0] = new Pion(Algorithm.isBlack());
		chessboard[6][1] = new Pion(Algorithm.isBlack());
		chessboard[6][2] = new Pion(Algorithm.isBlack());
		chessboard[6][3] = new Pion(Algorithm.isBlack());
		chessboard[6][4] = new Pion(Algorithm.isBlack());
		chessboard[6][5] = new Pion(Algorithm.isBlack());
		chessboard[6][6] = new Pion(Algorithm.isBlack());
		chessboard[6][7] = new Pion(Algorithm.isBlack());

		// White
		chessboard[0][0] = new Tour(Algorithm.isWhite());
		chessboard[0][1] = new Chevalier(Algorithm.isWhite());
		chessboard[0][2] = new Fou(Algorithm.isWhite());

		chessboard[0][3] = new Reine(Algorithm.isWhite());
		chessboard[0][4] = new Roi(Algorithm.isWhite());

		chessboard[0][5] = new Fou(Algorithm.isWhite());
		chessboard[0][6] = new Chevalier(Algorithm.isWhite());
		chessboard[0][7] = new Tour(Algorithm.isWhite());

		chessboard[1][0] = new Pion(Algorithm.isWhite());
		chessboard[1][1] = new Pion(Algorithm.isWhite());
		chessboard[1][2] = new Pion(Algorithm.isWhite());
		chessboard[1][3] = new Pion(Algorithm.isWhite());
		chessboard[1][4] = new Pion(Algorithm.isWhite());
		chessboard[1][5] = new Pion(Algorithm.isWhite());
		chessboard[1][6] = new Pion(Algorithm.isWhite());
		chessboard[1][7] = new Pion(Algorithm.isWhite());

		// TODO: Not used for now
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[j][i1] != null) {
					if (chessboard[j][i1].isColor()) {
						whites.add((Piece) chessboard[j][i1]);
					} else {
						blacks.add((Piece) chessboard[j][i1]);
					}
				}
			}
		}
	}

	/** Creates a new instance of Chessboard (used by clone) */
	private Chessboard(int _nWhite, int _nBlack) {

		nWhiteMoves = _nWhite;
		nBlackMoves = _nBlack;

		setWhites(new ArrayList<Piece>());
		setBlacks(new ArrayList<Piece>());

		chessboard = new Piece[8][8];
	}

	/**
	 * Apply a Move to the chessboard.
	 * 
	 * @param _thisMove
	 * @return false in case of errors.
	 */
	public boolean doMove(Move _thisMove) {

		int fromX, fromY, toX, toY;
		ArrayList<Move> moves;
		Piece piece;

		fromX = _thisMove.getStartX();
		fromY = _thisMove.getStartY();
		toX = _thisMove.getEndX();
		toY = _thisMove.getEndY();

		// Get piece
		piece = getPieceMouv(fromX, fromY);

		/* Remove the inDanger flag if a piece is no more in Danger */

		// Generate all possible moves for this piece
		moves = piece.generateMovesForThisPiece(this);
		// For each move find if it eats something
		for (Move move : moves) {
			Piece enemyPiece = (Piece) chessboard[move.getEndY()][move
					.getEndX()];
			if ((enemyPiece != null)
					&& (enemyPiece.isColor() != piece.isColor())) {
				enemyPiece.noMoreInDanger(); // I will no longer set it in
												// danger
			}
		}

		/* Apply changes */

		piece.setPosition(toX, toY);

		if (piece.isColor() && chessboard[toY][toX] != null) {
			blacks.remove(chessboard[toY][toX]);
		}
		if (!(piece.isColor()) && chessboard[toY][toX] != null) {
			whites.remove(chessboard[toY][toX]);
		}

		chessboard[toY][toX] = piece;
		chessboard[fromY][fromX] = null;

		// I moved
		piece.setMoved(true);

		/* Find if now there are pieces in danger */

		// Generate all possible moves for this piece
		moves = piece.generateMovesForThisPiece(this);

		// For each move find if it eats something
		for (Move move : moves) {
			Piece enemyPiece = (Piece) chessboard[move.getEndY()][move
					.getEndX()];
			if ((enemyPiece != null)
					&& (enemyPiece.isColor() != piece.isColor())) {
				enemyPiece.inDanger();
			}
		}

		return (true);
	}

	/**
	 * Return the Piece in the x/y position
	 * 
	 * @param _x
	 * @param _y
	 * @return The Piece at the position
	 */
	public Piece getPieceMouv(int _x, int _y) {

		try {
			return this.chessboard[_y][_x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Return the Piece in the x/y position
	 * 
	 * @param _x
	 * @param _y
	 * @return The Piece at the position
	 */
	public Piece getPiece(int _x, int _y) {
		try {
			return (Piece) chessboard[_y][_x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Return an array of all possible moves for a color.
	 * 
	 * @param _color
	 * @return An array of all possible moves
	 */
	public ArrayList<Move> generateAllPossibleMoves(boolean _color) {

		ArrayList<Move> moves = new ArrayList<Move>();
		List<Piece> temp;

		if (_color) {
			temp = whites;
		} else {
			temp = blacks;
		}

		for (Piece thisPiece : temp) {
			ArrayList<Move> movesForThisPiece = thisPiece.generateMovesForThisPiece(this);
			if (!(movesForThisPiece.isEmpty())) {
				for (Move move : movesForThisPiece) {
					moves.add(move);
				}
			}
		}

		if (_color) {
			setnWhiteMoves(moves.size());
		} else {
			setnBlackMoves(moves.size());
		}

		return moves;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {

		Chessboard chessboardClone = new Chessboard(getnWhiteMoves(),
				this.getnBlackMoves());

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.chessboard[y][x] != null) {
					Piece pieceClone = (Piece) chessboard[y][x].clone();
					chessboardClone.chessboard[y][x] = pieceClone;
					if (pieceClone.isColor()) {
						chessboardClone.whites.add((Piece) pieceClone);
					} else {
						chessboardClone.blacks.add((Piece) pieceClone);
					}
				} else {
					chessboardClone.chessboard[y][x] = null;
				}
			}
		}
		return chessboardClone;
	}

	/**
	 * Return the starting position (string) of a piece on the chessboard from
	 * it's ID.
	 * 
	 * @param _id
	 * @return The string identifying the position of the piece
	 */
	public static String startingPosition(int _id) {

		String position;

		switch (_id) {

		// Blacks
		case 1:
			return "a7";
		case 3:
			return "b7";
		case 5:
			return "c7";
		case 7:
			return "d7";
		case 9:
			return "e7";
		case 11:
			return "f7";
		case 13:
			return "g7";
		case 15:
			return "h7";
		case 25:
			return "a8";
		case 17:
			return "b8";
		case 21:
			return "c8";
		case 29:
			return "d8";
		case 31:
			return "e8";
		case 23:
			return "f8";
		case 19:
			return "g8";
		case 27:
			return "h8";

			// Whites TODO
			// pedone
		case 2: {
			position = "a2";
			break;
		}
		// pedone
		case 4: {
			position = "b2";
			break;
		}
		// pedone
		case 6: {
			position = "c2";
			break;
		}
		// pedone
		case 8: {
			position = "d2";
			break;
		}
		// pedone
		case 10: {
			position = "e2";
			break;
		}
		// pedone
		case 12: {
			position = "f2";
			break;
		}
		// pedone
		case 14: {
			position = "g2";
			break;
		}
		// pedone
		case 16: {
			position = "h2";
			break;
		}
		// torre sx
		case 26: {
			position = "a1";
			break;
		}
		// cavallo sx
		case 18: {
			position = "b1";
			break;
		}
		// alfiere sx
		case 22: {
			position = "c1";
			break;
		}
		// regina
		case 30: {
			position = "d1";
			break;
		}
		// re
		case 32: {
			position = "e1";
			break;
		}
		// alfiere dx
		case 24: {
			position = "f1";
			break;
		}
		// cavallo dx
		case 20: {
			position = "g1";
			break;
		}
		// torre dx
		case 28: {
			position = "h1";
			break;
		}

		default:
			return null;
		}
		return position;
	}

	// --- GETTER AND SETTER

	void setnWhiteMoves(int _nWhiteMoves) {
		this.nWhiteMoves = _nWhiteMoves;
	}

	int getnWhiteMoves() {
		return nWhiteMoves;
	}

	void setnBlackMoves(int _nBlackMoves) {
		this.nBlackMoves = _nBlackMoves;
	}

	int getnBlackMoves() {
		return nBlackMoves;
	}

	private void setBlacks(List<Piece> _blacks) {
		this.blacks = _blacks;
	}

	public List<Piece> getBlacks() {
		return blacks;
	}

	private void setWhites(List<Piece> _whites) {
		this.whites = _whites;
	}

	public List<Piece> getWhites() {
		return whites;
	}

}
