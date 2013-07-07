package fr.esgi.ia.test;

/**
 * Specifie un joueur de type HUMAIN.
 * 
 * @author C�dric TESNIERE
 */
public class JoueurHumain extends Joueur {

	/**
	 * Cr�e une nouvelle instance d'un joueur humain d'un certain type.
	 * 
	 * @param type
	 *            Type de joueur
	 */
	public JoueurHumain(int type) {
		super(type);
	}

	/**
	 * Retourne la categorie d'une joueur
	 * 
	 * @return int Retourne toujours Joueur.HUMAIN
	 * @since 1.2a
	 */
	public int getCategorie() {
		return HUMAIN;
	}
}
