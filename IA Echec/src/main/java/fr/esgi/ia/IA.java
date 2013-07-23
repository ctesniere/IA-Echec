package fr.esgi.ia;

/**
 * @author Cédric TESNIERE
 */
public class IA {

	private boolean myColor;

	private boolean enemyColor;

	private Chessboard globalChessboard;

	private Algorithm anAlgorithm;

	private boolean black = false;

	private boolean white = true;

	public IA() {

	}

	/**
	 * Appel un algorithme est lui donne la profondeur (difficulté de l'ia) et l'execute
	 * 
	 * @param _myColor
	 * @param _depth
	 * @param _chessboard
	 * @return Le meilleur mouvement
	 */
	public String play(boolean _myColor, int _depth, Chessboard _chessboard) {

		String output = "";
		boolean exitWhile = true;

		Algorithm anAlgorithm = new AlphaBeta(_depth);
		Move myMove;

		setGlobalChessboard(_chessboard);
		setAlgorithm(anAlgorithm);

		setMyColor(_myColor);
		setEnemyColor(!_myColor);

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

	public void setMyColor(boolean _myColor) {
		myColor = _myColor;
	}

	public boolean isEnemyColor() {
		return enemyColor;
	}

	public void setEnemyColor(boolean _enemyColor) {
		enemyColor = _enemyColor;
	}

	public Chessboard getGlobalChessboard() {
		return globalChessboard;
	}

	public void setGlobalChessboard(Chessboard _globalChessboard) {
		globalChessboard = _globalChessboard;
	}

	public Algorithm getAlgorithm() {
		return anAlgorithm;
	}

	public void setAlgorithm(Algorithm _algorithm) {
		anAlgorithm = _algorithm;
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean _black) {
		black = _black;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean _white) {
		white = _white;
	}

}
