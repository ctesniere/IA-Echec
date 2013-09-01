package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Algorithme d'élagage AlphaBeta
 * 
 * @author Cédric TESNIERE
 */
public class AlphaBeta extends Algorithm {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Constructeur par défaut
	 * 
	 * @param profondeur
	 */
	public AlphaBeta(int profondeur) {
		super(profondeur);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

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

		// Si counter (noeud) == profondeur, alors retourne la valeur
		// heuristique de échiquier
		if (counter >= profondeur) {
			return (chessValue);
		} else {
			counter++;
		}

		// Génère tous les fils de ce noeud (tous les coups possibles pour cette
		// couleur sur cet échiquier)
		ArrayList<Move> allPossibleMove = chessValue.getActualChessboardClone()
				.generateAllPossibleMoves(Helper.isColorWhite(color));

		// INFO Ce bloc de code existe a titre d'information pour le debug
		// Affichage des mouvements possible avant de continuer
		for (Move thisMove : allPossibleMove) {
			Piece piece = chessValue.getActualChessboardClone().getPiece(thisMove.getStartX(),
					thisMove.getStartY());
			System.out.println("Piece : " + piece.getClass().getSimpleName() + "("
					+ thisMove.getStartX() + ":" + thisMove.getStartY() + ") vers " + "("
					+ thisMove.getEndX() + ":" + thisMove.getEndY() + ")");
		}
		System.out.println("-----");

		for (Move thisMove : allPossibleMove) {

			// Nouvelle valeur de l'échiquier qui ont mon fils (déplacé) et mon
			// chemin
			ChessboardValue thisSon = new ChessboardValue(chessValue.getActualChessboardClone(),
					thisMove, chessValue.getMoves());

			if (thisSon.isLastMoveValid()) {
				if (Helper.isColorWhite(color)) {
					alpha = alpha.VSmax(alphaBetaAlg(thisSon, alpha, beta, !(color), counter));
				} else {
					beta = beta.VSmin(alphaBetaAlg(thisSon, alpha, beta, !(color), counter));
				}
			}
		}

		if (Helper.isColorWhite(color)) {
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

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	@Override
	public Move chooseMove(Chessboard chessboard, boolean color) {

		// Démarrage de l'échiquier
		ChessboardValue chessboardValue = new ChessboardValue(chessboard, null, null);

		// Création de la variable l'alpha
		ChessboardValue min = new ChessboardValue(chessboard, null, null);
		min.setValue(Integer.MIN_VALUE);

		// Création de la variable beta
		ChessboardValue max = new ChessboardValue(chessboard, null, null);
		max.setValue(Integer.MAX_VALUE);

		// AlphaBeta pruning
		ChessboardValue choice = alphaBetaAlg(chessboardValue, min, max, color, 0);

		return choice.getBestMove();
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
