package fr.esgi.ia;

/**
 * Classe mère des différents algo
 * 
 * @author Cédric TESNIERE
 */
abstract public class Algorithm {

	protected int profondeur;

	private static boolean black = false;

	private static boolean white = true;

	public Algorithm(int profondeur) {
		setProfondeur(profondeur);
	}

	/**
	 * Return to me the best move that can be done.
	 * 
	 * @param chessboard
	 * @param color
	 * @return The best move or NULL if no move is possible
	 */
	abstract public Move chooseMove(Chessboard chessboard, boolean color);

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int _profondeur) {
		profondeur = _profondeur;
	}

	static public boolean isBlack() {
		return Algorithm.black;
	}

	static public boolean isWhite() {
		return Algorithm.white;
	}

}
