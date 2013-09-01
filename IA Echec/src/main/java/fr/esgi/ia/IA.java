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
	 * Appel un algorithme est lui donne la profondeur (difficulté de l'ia) et
	 * l'execute. Déplace le meilleur pion sur le chessboard
	 * 
	 * @param myColor Ma couleur (Voir la classe Algorithm pour avoir les
	 *            valeurs)
	 * @param depth Profondeur de l'algorithme
	 * @param chessboard
	 * @return Le meilleur mouvement
	 */
	public String play(boolean myColor, int depth, Chessboard chessboard) {

		String output = "";
		Algorithm anAlgorithm = new AlphaBeta(depth);

		setGlobalChessboard(chessboard);
		setMyColor(myColor);

		// Boucle principale
		while (true) {
			Move myMove = anAlgorithm.chooseMove(getGlobalChessboard(), isMyColor());

			if (myMove == null) {
				output += "ERROR: Pas de mouvement possible.";
				break; // Sort de la boucle
			} else {
				getGlobalChessboard().doMove(myMove);
				output += myMove.moveOutputString() + " ";
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