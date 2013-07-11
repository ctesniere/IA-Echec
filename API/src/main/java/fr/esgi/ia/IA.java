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

	public void play(boolean _myColor, int _depth) {
		play(_myColor, _depth, new Chessboard());
	}

	/**
	 * Appel un algorithme est lui donne la profondeur (difficulté de l'ia) et
	 * l'execute
	 * 
	 * @param _myColor Coleur
	 * @param _depth Difficulté de l'application
	 */
	public void play(boolean _myColor, int _depth, Chessboard _chessboard) {

		Algorithm anAlgorithm = new AlphaBeta(_depth);
		Move myMove;

		setGlobalChessboard(new Chessboard());
		setAlgorithm(anAlgorithm);

		setMyColor(_myColor);
		setEnemyColor(!_myColor);

		// Boucle principale
		while (true) {

			myMove = anAlgorithm.chooseMove(getGlobalChessboard(), isMyColor());

			if (myMove == null) {
				System.err.println("ERROR: Pas de mouvement possible.");
				System.exit(0);
			} else {
				getGlobalChessboard().doMove(myMove); // TODO: Check return
														// value?
				System.out.println(myMove.moveOutputString());
			}
		}
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
