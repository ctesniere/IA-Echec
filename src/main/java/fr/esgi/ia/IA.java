package fr.esgi.ia;

/**
 * @author Cédric TESNIERE
 */
public class IA {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private boolean myColor;

	private boolean enemyColor;

	private Chessboard globalChessboard;

	private Algorithm algorithm;

	private boolean black = false;

	private boolean white = true;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public IA() {

	}

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
		Move myMove;

		setGlobalChessboard(chessboard);
		setAlgorithm(anAlgorithm);

		setMyColor(myColor);
		setEnemyColor(!myColor);

		// Boucle principale
		while (exitWhile) {

			myMove = anAlgorithm.chooseMove(getGlobalChessboard(), isMyColor());

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

	public boolean isMyColor() {
		return myColor;
	}

	public void setMyColor(boolean myColor) {
		this.myColor = myColor;
	}

	public boolean isEnemyColor() {
		return enemyColor;
	}

	public void setEnemyColor(boolean enemyColor) {
		this.enemyColor = enemyColor;
	}

	public Chessboard getGlobalChessboard() {
		return globalChessboard;
	}

	public void setGlobalChessboard(Chessboard globalChessboard) {
		this.globalChessboard = globalChessboard;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}
}
