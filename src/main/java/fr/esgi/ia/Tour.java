package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe représente la tour. La tour peut se déplacer horizontalement ou
 * verticalement. Cette pièce est à longue portée, c'est-à-dire qu'elle peut
 * être déplacée d'autant de cases qu'on le souhaite, sans pouvoir sauter par
 * dessus une autre pièce. Chaque camp possède deux tours. Elles se positionnent
 * sur les cases a1 et h1 pour les blancs et a8 et h8 pour les noirs.
 * 
 * @author Cédric TESNIERE
 */
public class Tour extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Création d'une instance d'une tour
	 * 
	 * @param color
	 */
	public Tour(boolean color) {
		super();
		setColor(color);

		if (isColor() == false)
			setValue(-500);
		else
			setValue(500);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * Test the possible move.
	 * 
	 * @param toX
	 * @param toY
	 * @param chessboard Actuel chessboard
	 * @return A Move or NULL.
	 */
	private Move checkMove(int toX, int toY, Chessboard chessboard, ArrayList<Move> moves) {

		Piece destination = chessboard.getPieceMouv(toX, toY);
		Move move;

		if (destination != null) { // If is not empty
			if (destination.isColor() != isColor()) { // Another color
				move = new Move(getX(), getY(), toX, toY, isColor());
				if (move.isValid())
					moves.add(move); // Add move
			}
			// Mine or not, if there is a piece STOP
			return null; // You must stop
		} else {
			move = new Move(getX(), getY(), toX, toY, isColor());
			if (move.isValid())
				moves.add(move); // Add move
		}

		return move;
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================
	
	/**
	 * Used when a chessboard must be cloned.
	 */
	@Override
	public Object clone() {
		Tour myClone = new Tour(isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(getValPos());
		return myClone;
	}

	/**
	 * The Rook can move until it finds an enemy of an ally piece. We give all
	 * possible moves, not the good ones.
	 * 
	 * @param chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {

		int toX = 0, toY = 0;
		ArrayList<Move> moves = new ArrayList<Move>();

		// 4 direction
		for (int direction = 0; direction < 4; direction++)
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
				Move move = checkMove(toX, toY, chessboard, moves);
				if (move == null) // If move is null, no more possible moves in
									// this direction
					break; // Stop going in this direction
			}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
