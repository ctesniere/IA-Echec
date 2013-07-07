package fr.esgi.ia.test;

/**
 * Specifie un joueur de type HUMAIN.
 * 
 * @author Cédric TESNIERE
 */
public class JoueurHumain extends Joueur {

	/**
	 * Crée une nouvelle instance d'un joueur humain d'un certain type.
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
