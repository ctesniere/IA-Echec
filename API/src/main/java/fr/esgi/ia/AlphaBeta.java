package fr.esgi.ia;

import java.util.ArrayList;

/**
 * AlphaBeta pruning algorithm.
 * 
 * @author Cédric TESNIERE
 */
public class AlphaBeta extends Algorithm {

	public AlphaBeta(int _profondeur) {
		super(_profondeur);
	}

	public AlphaBeta() {
		super(2);
	}

	@Override
	public Move chooseMove(Chessboard _chessboard, boolean _color) {

		// Starting chessboard
		ChessboardValue chessboardValue = new ChessboardValue(_chessboard, null, null);

		// Création de la variable l'alpha
		ChessboardValue min = new ChessboardValue(_chessboard, null, null);
		min.setValue(Integer.MIN_VALUE);

		// Création de la variable beta
		ChessboardValue max = new ChessboardValue(_chessboard, null, null);
		max.setValue(Integer.MAX_VALUE);

		// AlphaBeta pruning
		ChessboardValue choice = alphaBetaAlg(chessboardValue, min, max, _color, 0);

		return choice.getBestMove();
	}

	/**
	 * Algorithme Alpha beta
	 * 
	 * @param _chessValue
	 * @param _alpha
	 * @param _beta
	 * @param _color
	 * @param _counter
	 * @return ChessboardValue
	 */
	private ChessboardValue alphaBetaAlg(ChessboardValue _chessValue, ChessboardValue _alpha,
			ChessboardValue _beta, boolean _color, int _counter) {

		// If node == leaf then return heuristic value of chessboard
		if (_counter >= profondeur)
			return (_chessValue);
		else
			_counter++;

		// Génère tous les fils de ce noeud
		// (tous les coups possibles pour cette couleur sur cet échiquier)
		ArrayList<Move> allPossibleMove = _chessValue.getActualChessboard().generateAllPossibleMoves(_color);

		for (Move thisMove : allPossibleMove) {

			// Nouvelle valeur de l'échiquier qui ont mon fils (déplacé) et mon
			// chemin
			ChessboardValue thisSon = new ChessboardValue(_chessValue.getActualChessboard(), thisMove,
					_chessValue.getMoves());
			if (thisSon.isLastMoveValid())
				if (_color)
					_alpha = _alpha.VSmax(alphaBetaAlg(thisSon, _alpha, _beta, !(_color), _counter));
				else
					_beta = _beta.VSmin(alphaBetaAlg(thisSon, _alpha, _beta, !(_color), _counter));
		}

		if (_color) {
			if (_beta.getValue() <= _alpha.getValue())
				return _beta;
			else
				return _alpha;
		} else // Retourne le minimum
		if (_beta.getValue() <= _alpha.getValue())
			return _alpha;
		else
			return _beta;
	}

}
