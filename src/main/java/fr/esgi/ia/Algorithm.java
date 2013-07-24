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

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	public static boolean isBlack() {
		return black;
	}

	public static void setBlack(boolean black) {
		Algorithm.black = black;
	}

	public static boolean isWhite() {
		return white;
	}

	public static void setWhite(boolean white) {
		Algorithm.white = white;
	}

}
