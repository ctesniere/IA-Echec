package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe, utilis� dans le processus AlphaBeta, stocke un �chiquier, la valeur et d'autres informations
 * utiles.
 * 
 * @author C�dric TESNIERE
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
	 * Cr�ation d'un objet de type ChessboardValue
	 * 
	 * @param chessboard
	 * @param move
	 * @param earlyMoves
	 */
	public ChessboardValue(Chessboard chessboard, Move move, ArrayList<Move> earlyMoves) {

		actualChessboardClone = (Chessboard) chessboard.clone();
		moves = new ArrayList<Move>();

		// Si ce n'est pas la premi�re fois [<> NULL (=> <> forme alpha ou b�ta)]
		if (move != null) {
			color = move.isColor();
			// Faire le mouvement et d�finir la validit�
			if (!(actualChessboardClone.doMove(move))) {
				move.setValid(false);
			} else {
				lastMove = move;
				lastMove.setValid(true);

				// Let's copy the moves
				// TODO: Clone?
				if (earlyMoves != null) {
					for (Move thisMove : earlyMoves)
						moves.add(thisMove);
				}
				// Ajout de ce mouvement
				moves.add(move);
			}
		}

		// Trouvez et sauvegarder la valeur de l'�chiquier
		value = chessboardValue(color);
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
	 * Retour au min entre cet �chiquier et celle pass�e.
	 * 
	 * @param elseVChessboard L'autre �chiquier
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
	 * Retour au maximum entre cet �chiquier et celle pass�e.
	 * 
	 * @param elseVChessboard Les autres �chiquiers
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
			return lastMove.isValid();
		else
			return false;
	}

	/**
	 * Retourner la valeur de l'�chiquier pour une couleur
	 * 
	 * @param color La couleur du joueur
	 * @return La valeur de l'echiquier
	 */
	public int chessboardValue(boolean color) {

		int val = 0;
		int temp;

		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				// Si la case n'est pas un blanc
				if (getActualChessboardClone().getPieceMouv(x, y) != null) {
					Piece piece = getActualChessboardClone().getPieceMouv(x, y);
					// Si la pi�ce n'est pas en danger et il est ma couleur
					if ((!piece.isInDanger()) && (piece.isColor() == color)) // et d�plac�
						if (piece.isMoved()) {
							// Note normale
							temp = piece.getValue() + piece.getPositionValue();
							val = val + temp;
						} else // Si elle est noire
						if (piece.isColor() == false) {
							// Si Tour ou roi
							// if ((piece.getId() == 31) || (piece.getId() ==
							// 27) || (piece.getId() == 25)) {
							if (true) {
								// Note normale
								temp = piece.getValue() + piece.getPositionValue();
								val = val + temp;
							}
							// Si une pi�ce est un autre
							else {
								// D'un avantage pour le blanc 10
								temp = piece.getValue() + piece.getPositionValue();
								val = val + temp + 10;
							}
						} else // Si vous �tes une de mes tours ou mon roi
						// if ((piece.getId() == 32) || (piece.getId() == 28) ||
						// (piece.getId() == 26)) {
						if (true) {
							// Valutazione normale
							temp = piece.getValue() + piece.getPositionValue();
							val = val + temp;
						}
						// Si une pi�ce est un autre
						else {
							// Par un bord noir avec 10
							temp = piece.getValue() + piece.getPositionValue();
							val = (val + temp) - 10;
						}
					// Si la pi�ce est en danger et ce n'est pas ma couleur
					if ((piece.isInDanger()) && (piece.isColor() != color)) {
						// Dai un vantaggio del 15% del valore del pezzo
						temp = (piece.getValue() * 15) / 100;
						val = val - temp;
					}
					// Si la pi�ce n'est pas ma couleur
					if (piece.isColor() != color) {
						// Note normale
						temp = piece.getValue() + piece.getPositionValue();
						val = val + temp;
					}
				}
		// Noter cette mobilit�
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
