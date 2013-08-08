package fr.esgi.ia;

/**
 * @author Cédric TESNIERE
 */
public class IA {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private boolean myColor;

	private Chessboard globalChessboard;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public IA() {

	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * Appel un algorithme est lui donne la profondeur (difficulté de l'ia) et l'execute
	 * 
	 * @param myColor
	 * @param depth
	 * @param chessboard
	 * @return Le meilleur mouvement
	 */
	public String play(boolean myColor, int depth, Chessboard chessboard) {

		String output = "";
		boolean exitWhile = true;
		Algorithm anAlgorithm = new AlphaBeta(depth);

		setGlobalChessboard(chessboard);
		setMyColor(myColor);

		// Boucle principale
		while (exitWhile) {

			Move myMove = anAlgorithm.chooseMove(getGlobalChessboard(), isMyColor());

			if (myMove == null) {
				output+= "ERROR: Pas de mouvement possible.";
				exitWhile = false;
			} else {
				getGlobalChessboard().doMove(myMove);
				output+= myMove.moveOutputString() + " ";
			}
		}
		return output;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public boolean isMyColor() {
		return myColor;
	}

	public void setMyColor(boolean myColor) {
		this.myColor = myColor;
	}

	public Chessboard getGlobalChessboard() {
		return globalChessboard;
	}

	public void setGlobalChessboard(Chessboard globalChessboard) {
		this.globalChessboard = globalChessboard;
	}
}
