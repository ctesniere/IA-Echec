package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente le fou. Cette pièce est à longue portée, c'est-à-dire
 * qu'elle peut être déplacée d'autant de cases qu'on le souhaite, sans pouvoir
 * sauter par dessus une autre pièce. Il ne peut changer de couleur de case
 * durant la partie et ne balaie donc que la moitié de l'échiquier. A l'instar
 * du cavalier, le fou est une pièce mineure. En général, on lui attribue la
 * même valeur que le cavalier, la différence dépendant de la position sur
 * l'échiquier.
 * 
 * @author Cédric TESNIERE
 */
public class Fou extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Création d'une instance fou
	 * 
	 * @param color
	 */
	public Fou(int id, boolean color) {
		super(id, color, 325);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	@Override
	public Object clone() {
		Fou myClone = new Fou(getId(), isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		return myClone;
	}

	/**
	 * The Bishop can move until it finds an enemy of an ally piece. We give all
	 * possible moves, not the good ones.
	 * 
	 * @param chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {

		int toX = -1, toY = -1;
		ArrayList<Move> moves = new ArrayList<Move>();

		String positionPiece = chessboard.getPositionPiece(this);
		int getX = Helper.getXfromString(positionPiece);
		int getY = Helper.getYfromString(positionPiece);

		// 4 direction
		for (int direction = 0; direction < 4; direction++)
			// Max 8 moves
			for (int length = 1; length < 9; length++) {

				if (direction == 0) {
					toX = getX + length;
					toY = getY + length;
				}
				if (direction == 1) {
					toX = getX - length;
					toY = getY + length;
				}
				if (direction == 2) {
					toX = getX - length;
					toY = getY - length;
				}
				if (direction == 3) {
					toX = getX + length;
					toY = getY - length;
				}

				Move move = checkThis(toX, toY, chessboard);

				// Si déplacement est nul, plus possible de ce déplacer dans
				// cette direction pour le fou
				if (move != null)
					moves.add(move);
				else
					break;
			}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
