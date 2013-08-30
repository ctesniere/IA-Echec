package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe, utilisé dans le processus AlphaBeta, stocke un échiquier, la valeur et d'autres informations
 * utiles.
 * 
 * @author Cédric TESNIERE
 */
public class ChessboardValue {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private Chessboard actualChessboardClone = null;

	private Move lastMove = null;

	private ArrayList<Move> moves;

	private int value;

	private boolean color;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Création d'un objet de type ChessboardValue
	 * 
	 * @param chessboard
	 * @param move
	 * @param earlyMoves
	 */
	public ChessboardValue(Chessboard chessboard, Move move, ArrayList<Move> earlyMoves) {

		actualChessboardClone = (Chessboard) chessboard.clone();
		moves = new ArrayList<Move>();

		// Si ce n'est pas la première fois [<> NULL (=> <> forme alpha ou béta)]
		if (move != null) {
			color = move.isColor();
			lastMove = move;

			// Let's copy the moves
			// TODO: Clone?
			if (earlyMoves != null) {
				for (Move thisMove : earlyMoves)
					moves.add(thisMove);
			}
			// Ajout de ce mouvement
			moves.add(move);
		}

		// Trouvez et sauvegarder la valeur de l'échiquier
		value = chessboardValueForColor(color); // INFO value : 160
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * Get the better move on the list.
	 * 
	 * @return The best possible move
	 */
	public Move getBestMove() {
		if (getMoves().isEmpty())
			return null;
		else
			return getMoves().get(0);
	}

	/**
	 * Retour au min entre cet échiquier et celle passée.
	 * 
	 * @param elseVChessboard L'autre échiquier
	 * @return Le min entre les deux
	 */
	public ChessboardValue VSmin(ChessboardValue elseVChessboard) {

		if ((lastMove == null) && (elseVChessboard.lastMove == null))
			if (getValue() < elseVChessboard.getValue())
				return this;
			else
				return elseVChessboard;

		if (lastMove == null)
			return elseVChessboard;

		if (elseVChessboard.lastMove == null)
			return this;

		if (getValue() < elseVChessboard.getValue())
			return this;
		else
			return elseVChessboard;
	}

	/**
	 * Retour au maximum entre cet échiquier et celle passée.
	 * 
	 * @param elseVChessboard Les autres échiquiers
	 * @return Le max entre les deux
	 */
	public ChessboardValue VSmax(ChessboardValue elseVChessboard) {

		if ((lastMove == null) && (elseVChessboard.lastMove == null))
			if (getValue() > elseVChessboard.getValue())
				return this;
			else
				return elseVChessboard;

		if (lastMove == null)
			return elseVChessboard;

		if (elseVChessboard.lastMove == null)
			return this;

		if (getValue() > elseVChessboard.getValue())
			return this;
		else
			return elseVChessboard;
	}

	public boolean isLastMoveValid() {
		if (lastMove != null)
			return lastMove.checkValidity();
		else
			return false;
	}

	/**
	 * Retourner la valeur de l'échiquier pour une couleur
	 * 
	 * @param color La couleur du joueur
	 * @return La valeur de l'echiquier
	 */
	public int chessboardValueForColor(boolean color) {

		int val = 0;
		int temp;

		// On parcours l'échiquier
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				// Si la case n'est pas un blanc
				if (getActualChessboardClone().getPiece(x, y) != null) {
					Piece piece = getActualChessboardClone().getPiece(x, y);

					// Si la pièce n'est pas en danger et il est ma couleur
					if ((!piece.isInDanger()) && (piece.isColor() == color)) { // et déplacé

						if (piece.isMoved()) {
							// Note normale
							temp = piece.getValue();
							val = val + temp;
						} else {
							// Si Tour ou Roi
							if (piece.getClass().getSimpleName().equals(TypePiece.KING) || piece.getClass().getSimpleName().equals(TypePiece.ROOK)) {
								// Note normale
								temp = piece.getValue();
								val = val + temp;
							} else { // Si une pièce est un autre
								if (piece.isColor() == Algorithm.isBlack()) { // Si la pièce est noire
									// D'un avantage pour le blanc 10
									temp = piece.getValue();
									val = val + temp + 10;
								} else {
									// Par un bord noir avec 10
									temp = piece.getValue();
									val = (val + temp) - 10;
								}
							}
						}
					}

					// Si la pièce est en danger et ce n'est pas ma couleur
					if ((piece.isInDanger()) && (piece.isColor() != color)) {
						// Dai un vantaggio del 15% del valore del pezzo
						temp = (piece.getValue() * 15) / 100;
						val = val - temp;
					}

					// Si la pièce n'est pas ma couleur
					if (piece.isColor() != color) {
						// Note normale
						temp = piece.getValue();
						val = val + temp;
					}
				}
			}
		}

		// Noter cette mobilité
		temp = getActualChessboardClone().getNbWhiteMoves() - getActualChessboardClone().getNbBlackMoves();
		val += temp * 2;
		return val;
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public Chessboard getActualChessboardClone() {
		return actualChessboardClone;
	}

	public void setActualChessboardClone(Chessboard actualChessboardClone) {
		this.actualChessboardClone = actualChessboardClone;
	}

	public Move getLastMove() {
		return lastMove;
	}

	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
}
