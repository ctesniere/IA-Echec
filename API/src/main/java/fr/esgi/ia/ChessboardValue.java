package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe, utilis� dans le processus AlphaBeta, stocke un �chiquier, la valeur et d'autres informations
 * utiles.
 * 
 * @author C�dric TESNIERE
 */
public class ChessboardValue {

	private Chessboard actualChessboardClone = null;

	private Move lastMove = null;

	private ArrayList<Move> moves;

	private int value;

	private boolean color;

	/**
	 * Cr�ation d'un objet de type ChessboardValue
	 * 
	 * @param _chessboard
	 * @param _move
	 * @param _earlyMoves
	 */
	public ChessboardValue(Chessboard _chessboard, Move _move, ArrayList<Move> _earlyMoves) {

		actualChessboardClone = (Chessboard) _chessboard.clone();
		moves = new ArrayList<Move>();

		// Si ce n'est pas la premi�re fois [<> NULL (=> <> forme alpha ou b�ta)]
		if (_move != null) {
			color = _move.isColour();
			// Faire le mouvement et d�finir la validit�
			if (!(actualChessboardClone.doMove(_move))) {
				_move.setValid(false);
			} else {
				lastMove = _move;
				lastMove.setValid(true);

				// Let's copy the moves
				// TODO: Clone?
				if (_earlyMoves != null) {
					for (Move thisMove : _earlyMoves)
						moves.add(thisMove);
				}
				// Ajout de ce mouvement
				moves.add(_move);
			}
		}

		// Trouvez et sauvegarder la valeur de l'�chiquier
		value = chessboardValue(color);
	}

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
	 * @param _elseVChessboard L'autre �chiquier
	 * @return Le min entre les deux
	 */
	public ChessboardValue VSmin(ChessboardValue _elseVChessboard) {

		if ((lastMove == null) && (_elseVChessboard.lastMove == null))
			if (getValue() < _elseVChessboard.getValue())
				return this;
			else
				return _elseVChessboard;

		if (lastMove == null)
			return _elseVChessboard;

		if (_elseVChessboard.lastMove == null)
			return this;

		if (getValue() < _elseVChessboard.getValue())
			return this;
		else
			return _elseVChessboard;
	}

	/**
	 * Retour au maximum entre cet �chiquier et celle pass�e.
	 * 
	 * @param _elseVChessboard Les autres �chiquiers
	 * @return Le max entre les deux
	 */
	public ChessboardValue VSmax(ChessboardValue _elseVChessboard) {

		if ((lastMove == null) && (_elseVChessboard.lastMove == null))
			if (getValue() > _elseVChessboard.getValue())
				return this;
			else
				return _elseVChessboard;

		if (lastMove == null)
			return _elseVChessboard;

		if (_elseVChessboard.lastMove == null)
			return this;

		if (getValue() > _elseVChessboard.getValue())
			return this;
		else
			return _elseVChessboard;
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
	 * @param _color La couleur du joueur
	 * @return La valeur de l'echiquier
	 */
	public int chessboardValue(boolean _color) {

		int val = 0;
		int temp;

		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				// Si la case n'est pas un blanc
				if (getActualChessboard().getPieceMouv(x, y) != null) {
					Piece piece = getActualChessboard().getPieceMouv(x, y);
					// Si la pi�ce n'est pas en danger et il est ma couleur
					if ((!piece.isInDanger()) && (piece.isColor() == _color)) // et d�plac�
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
					if ((piece.isInDanger()) && (piece.isColor() != _color)) {
						// Dai un vantaggio del 15% del valore del pezzo
						temp = (piece.getValue() * 15) / 100;
						val = val - temp;
					}
					// Si la pi�ce n'est pas ma couleur
					if (piece.isColor() != _color) {
						// Note normale
						temp = piece.getValue() + piece.getPositionValue();
						val = val + temp;
					}
				}
		// Noter cette mobilit�
		temp = getActualChessboard().getnWhiteMoves() - getActualChessboard().getnBlackMoves();
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
		actualChessboardClone = actualChessboard;
	}

	public Chessboard getActualChessboard() {
		return actualChessboardClone;
	}

}
