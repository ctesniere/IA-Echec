package fr.esgi.ia;

/**
 * Classe mere des différents algo
 * 
 * @author Cédric TESNIERE
 */
public abstract class Algorithm {

	protected int profondeur;

	public static final boolean BLACK = false;

	public static final boolean WHITE = true;

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
	public abstract Move chooseMove(Chessboard chessboard, boolean color);

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

}
