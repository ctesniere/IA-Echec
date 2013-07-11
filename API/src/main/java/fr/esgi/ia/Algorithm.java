package fr.esgi.ia;

/**
 * Classe m�re des diff�rents algo
 * 
 * @author C�dric TESNIERE
 */
abstract public class Algorithm {

	protected int profondeur;

	private static boolean black = false;

	private static boolean white = true;

	public Algorithm(int _profondeur) {
		profondeur = _profondeur;
	}

	/**
	 * Return to me the best move that can be done.
	 * 
	 * @param _chessboard
	 * @param _color
	 * @return The best move or NULL if no move is possible
	 */
	abstract public Move chooseMove(Chessboard _chessboard, boolean _color);

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