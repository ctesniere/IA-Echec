package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Algorithme d'�lagage AlphaBeta
 * 
 * @author C�dric TESNIERE
 */
public class AlphaBeta extends Algorithm {

	public AlphaBeta(int profondeur) {
		super(profondeur);
	}

	public AlphaBeta() {
		super(2);
	}

	@Override
	public Move chooseMove(Chessboard chessboard, boolean color) {

		// D�marrage de l'�chiquier
		ChessboardValue chessboardValue = new ChessboardValue(chessboard, null, null);

		// Cr�ation de la variable l'alpha
		ChessboardValue min = new ChessboardValue(chessboard, null, null);
		min.setValue(Integer.MIN_VALUE);

		// Cr�ation de la variable beta
		ChessboardValue max = new ChessboardValue(chessboard, null, null);
		max.setValue(Integer.MAX_VALUE);

		// AlphaBeta pruning
		ChessboardValue choice = alphaBetaAlg(chessboardValue, min, max, color, 0);

		return choice.getBestMove();
	}

	/**
	 * Algorithme Alpha beta
	 * 
	 * @param chessValue
	 * @param alpha
	 * @param beta
	 * @param color
	 * @param counter
	 * @return ChessboardValue
	 */
	private ChessboardValue alphaBetaAlg(ChessboardValue chessValue, ChessboardValue alpha,
			ChessboardValue beta, boolean color, int counter) {

		// Si le noeud == feuille alors retourne la valeur heuristique de �chiquier
		if (counter >= profondeur) {	
			return (chessValue);
		} else {
			counter++;
		}

		// G�n�re tous les fils de ce noeud (tous les coups possibles pour cette couleur sur cet �chiquier)
		ArrayList<Move> allPossibleMove = chessValue.getActualChessboardClone().generateAllPossibleMoves(color);

		for (Move thisMove : allPossibleMove) {

			// Nouvelle valeur de l'�chiquier qui ont mon fils (d�plac�) et mon chemin
			ChessboardValue thisSon = new ChessboardValue(chessValue.getActualChessboardClone(), thisMove,
					chessValue.getMoves());
			if (thisSon.isLastMoveValid()) {
				if (color) {
					alpha = alpha.VSmax(alphaBetaAlg(thisSon, alpha, beta, !(color), counter));
				} else {
					beta = beta.VSmin(alphaBetaAlg(thisSon, alpha, beta, !(color), counter));
				}
			}
		}

		if (color) {
			if (beta.getValue() <= alpha.getValue())
				return beta;
			else
				return alpha;
		} else { // Retourne le minimum (Alpha)
			if (beta.getValue() <= alpha.getValue())
				return alpha;
			else
				return beta;
		}
	}

}
