package fr.esgi.ia;

/**
 * Classe mère des différents algo
 * 
 * @author Cédric TESNIERE
 */
abstract public class Algorithm {

	protected int profondeur;

	private boolean black = false;

	private boolean white = true;

	static String chessBoard[][] = {
			{ "r", "k", "b", "q", "a", "b", "k", "r" },
			{ "p", "p", "p", "p", "p", "p", "p", "p" },
			{ " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " " },
			{ "P", "P", "P", "P", "P", "P", "P", "P" },
			{ "R", "K", "B", "Q", "A", "B", "K", "R" } };

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
		this.profondeur = _profondeur;
	}

}