package fr.esgi.ia.test;

/**
 * Classe permettant de definir un joueur.
 * 
 * @author C�dric TESNIERE
 */
public abstract class Joueur {

	/**
	 * Premier joueur
	 */
	public static final int MAX = 1;

	/**
	 * Second joueur
	 */
	public static final int MIN = 2;

	/**
	 * Aucun des deux joueurs 
	 */
	public static final int VIDE = 0;

	/**
	 * Represente la categorie du joueur machine
	 */
	public static final int AI = 5;

	/**
	 * Represente la categorie du joueur humain
	 */
	public static final int HUMAIN = 6;
	/**
	 * Num�ro du joueur.
	 */
	protected int type;

	/**
	 * Compteur de partie gagn�es
	 */
	private int cptSectionWin;

	/**
	 * Cr�e une nouvelle instance d'un joueur d'un certain type.
	 * 
	 * @param type Type de joueur
	 */
	public Joueur(int type) {
		this.type = type;
		cptSectionWin = 0;
	}

	/**
	 * Retourne le type d'un joueur.
	 * 
	 * @return int Type de joueur
	 */
	public int getType() {
		return type;
	}

	/**
	 * Retourne le nombre de partie gagn�es par ce joueur.
	 * 
	 * @return Nombre de parties gagng�es
	 */
	public int getCompteur() {
		return cptSectionWin;
	}

	/**
	 * Incr�mente de 1, le nombre de parties gagn�es.
	 */
	public void incCompteur() {
		cptSectionWin++;
	}

	/**
	 * Reset le compteur de parties gagn�es.
	 */
	public void resetCompteur() {
		cptSectionWin = 0;
	}

	/**
	 * Retourne la categorie d'un joueur HUMAIN ou AI
	 * 
	 * @return int Categorie du joueur
	 * @since 1.2
	 */
	public abstract int getCategorie();
}
