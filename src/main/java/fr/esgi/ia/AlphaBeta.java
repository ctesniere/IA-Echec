package fr.esgi.ia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Algorithme d'élagage AlphaBeta
 * 
 * @author Cédric TESNIERE
 */
public class AlphaBeta extends Algorithm {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * Constructeur par défaut
	 * 
	 * @param profondeur
	 */
	public AlphaBeta(int profondeur) {
		super(profondeur);
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

		// Si counter (noeud) == profondeur, alors retourne la valeur
		// heuristique de échiquier
		if (counter >= profondeur) {
			return (chessValue);
		} else {
			counter++;
		}

		// Génère tous les fils de ce noeud (tous les coups possibles pour cette
		// couleur sur cet échiquier)
		final ArrayList<Move> allPossibleMove = chessValue.getActualChessboardClone()
				.generateAllPossibleMoves(color);
		printAllPossibleMove(allPossibleMove, counter, chessValue);

		for (final Move thisMove : allPossibleMove) {

			// Definie une valeur au noeud
			final ChessboardValue thisSon = new ChessboardValue(chessValue.getActualChessboardClone(),
					thisMove, chessValue.getMoves());

			printThisMoveNoeuds(thisMove, thisSon, counter);

			if (thisSon.isLastMoveValid()) {
				if (Helper.isColorWhite(color)) {
					alpha = alpha.VSmax(alphaBetaAlg(thisSon, alpha, beta, !(color), counter));
				} else {
					beta = beta.VSmin(alphaBetaAlg(thisSon, alpha, beta, !(color), counter));
				}
			}
		}

		if (Helper.isColorWhite(color)) {
			return beta.getValue() <= alpha.getValue() ? beta : alpha;
		} else { // Retourne le minimum (Alpha)
			return beta.getValue() <= alpha.getValue() ? alpha : beta;
		}
	}

	/**
	 * INFO Ce bloc de code existe a titre d'information pour le debug Affichage
	 * des mouvements possible avant de continuer
	 * 
	 * @param allPossibleMove
	 * @param counter
	 */
	public void printAllPossibleMove(ArrayList<Move> allPossibleMove, int counter,
			ChessboardValue chessValue) {

		// Affiche les mouvements possible du premier noeud
		if (counter == 1) {
			for (final Move thisMove : allPossibleMove) {
				final Piece piece = chessValue.getActualChessboardClone().getPiece(thisMove.getStartX(),
						thisMove.getStartY());
				LOGGER.info(piece.getClass().getSimpleName() + "("
						+ Helper.getStringFromPosition(thisMove.getStartX(), thisMove.getStartY())
						+ ") -> " + "("
						+ Helper.getStringFromPosition(thisMove.getEndX(), thisMove.getEndY())
						+ ")");
			}
			LOGGER.info("-----");
		}
	}

	public void printThisMoveNoeuds(Move thisMove, ChessboardValue thisSon, int counter) {
		for (int i = 10; i < (counter * 10); i++) {
			LOGGER.info(" ");
		}
		LOGGER.info("Depth: " + counter + "; Mouv: "
				+ Helper.getStringFromPosition(thisMove.getStartX(), thisMove.getStartY()) + " -> "
				+ Helper.getStringFromPosition(thisMove.getEndX(), thisMove.getEndY())
				+ "; valeur: " + thisSon.getValue());
	}

	@Override
	public Move chooseMove(Chessboard chessboard, boolean color) {

		// Démarrage de l'échiquier
		final ChessboardValue chessboardValue = new ChessboardValue(chessboard, null, null);

		// Création de la variable l'alpha
		final ChessboardValue alpha = new ChessboardValue(chessboard, null, null);
		alpha.setValue(Integer.MIN_VALUE);

		// Création de la variable beta
		final ChessboardValue beta = new ChessboardValue(chessboard, null, null);
		beta.setValue(Integer.MAX_VALUE);

		// AlphaBeta pruning
		final ChessboardValue choice = alphaBetaAlg(chessboardValue, alpha, beta, color, 0);

		return choice.getBestMove();
	}
}
