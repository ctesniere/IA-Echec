package fr.esgi.ia.test;

import javax.security.auth.login.Configuration;

/**
 * Specifie un joueur de type AI.
 * 
 * @author Cédric TESNIERE
 */
public class JoueurAI extends Joueur {

	/**
	 * Niveau de l'AI
	 */
	int profondeur;
	
	/**
	 * Nombre de coups calculés par l'AI
	 */
	int nbCoups;

	/**
	 * Crée une nouvelle instance d'un joueur IA d'un certain type.
	 * 
	 * @param type
	 *            Type de joueur
	 */
	public JoueurAI(int type) {
		super(type);
		profondeur = 4;
	}

	/**
	 * Calcul le meilleur coup pour l'ordi
	 * 
	 * @return int Meilleur coup de l'IA
	 * @since 1.2
	 */
	public int coupOrdi() {
		int[][] position = new int[3][3];
		int coupsuiv = 0;
		nbCoups = 0;
		int temp;
		int max;
		Morpion m = Morpion.getMorpion();
		
		/* Adapte l'algorithme à un type de joueur MIN ou MAX */
		if (getType() == Joueur.MIN) {
			max = 1;
		} else {
			max = -1;
		}

		int p = profondeur;

		/* Pour tous les coups possibles */
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				/* Recopie la position */
				for (int a = 0; a < 3; a++)
					for (int b = 0; b < 3; b++)
						position[a][b] = Morpion.getConfiguration()
								.getTableauPosition()[a][b];

				/* Test pour savoir si un coup a déjà été joué dans cette case */
				if (position[i][j] == Joueur.VIDE) {
					if ((position[2][2] == Joueur.MAX)
							&& (position[1][2] == Joueur.MAX)
							&& (position[0][2] == Joueur.VIDE))
						return 2;
					if (getType() == Joueur.MIN) {
						position[i][j] = Joueur.MIN;
						temp = Minimax(position, Joueur.MAX, -1, p);
						if (temp <= max) {
							max = temp;
							coupsuiv = i * 3 + j;
						}
					} else {
						position[i][j] = Joueur.MAX;
						temp = Minimax(position, Joueur.MIN, 1, p);
						if (temp >= max) {
							max = temp;
							coupsuiv = i * 3 + j;
						}
					}
				}
			}
		}
		Morpion.getMorpion().getInterface().getOption()
				.setNbCoups(nbCoups, getType());
		return coupsuiv;
	}

	public int Minimax(int[][] pos, int joueur, int max, int p) {

		int[][] position = new int[3][3];
		int temp;
		nbCoups++; /* Incrementation du nbre de cps calculés */
		if ((p <= 0) || PosFinal(pos)) {
			if (profondeur < 6) {
				return new Configuration(pos).evaluation();
			}
			return Eval(pos);
		} else {
			/* Pour tous les coups restants */
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					/* Recopie la position */
					for (int a = 0; a < 3; a++)
						for (int b = 0; b < 3; b++)
							position[a][b] = pos[a][b];

					/*
					 * Test por savoir si un coup a déjà été joué dans cette
					 * case
					 */
					if (position[i][j] == Joueur.VIDE) {
						if (joueur == Joueur.MIN) {
							position[i][j] = Joueur.MIN;
							temp = Minimax(position, Joueur.MAX, -1, p - 1);
							if (temp <= max)
								max = temp;
						} else {
							position[i][j] = Joueur.MAX;
							temp = Minimax(position, Joueur.MIN, 1, p - 1);
							if (temp >= max)
								max = temp;
						}
					}
				}
			}
			return max;
		}
	}

	/**
	 * Méthode qui teste si une ligne est gagnante
	 * 
	 * @param pos
	 *            Tableau de position
	 * @return int Le joueur auquel appartient la ligne
	 * @since 1.2a
	 */
	public int Ligne(int[][] pos) {
		for (int i = 0; i < 3; i++) {
			if ((pos[0][i] == pos[1][i]) && (pos[1][i] == pos[2][i]))
				return pos[0][i];
		}
		return Joueur.VIDE;
	}

	/**
	 * Méthode qui teste si une colonne est gagnante
	 * 
	 * @param pos
	 *            Tableau de position
	 * @return int Le joueur auquel appartient la colonne
	 * @since 1.2a
	 */
	public int Colonne(int[][] pos) {
		for (int i = 0; i < 3; i++) {
			if ((pos[i][0] == pos[i][1]) && (pos[i][1] == pos[i][2]))
				return pos[i][0];
		}
		return Joueur.VIDE;
	}

	/**
	 * Méthode qui teste si une diagonale est gagnante
	 * 
	 * @param pos
	 *            Tableau de positions
	 * @return int Joueur auquel appartient la diagonale
	 * @since 1.2a
	 */
	public int Diagonale(int[][] pos) {
		if ((pos[0][0] == pos[1][1]) && (pos[1][1] == pos[2][2]))
			return pos[0][0];
		else if ((pos[2][0] == pos[1][1]) && (pos[1][1] == pos[0][2]))
			return pos[2][0];
		else
			return Joueur.VIDE;
	}

	/**
	 * Méthode qui teste si une position est gagnante
	 * 
	 * @param pos
	 *            Tableau de positions
	 * @return int Joueur auquel appartient la diagonale
	 * @since 1.2a
	 */
	public int Eval(int[][] pos) {
		if ((Ligne(pos) == Joueur.MAX) || (Colonne(pos) == Joueur.MAX)
				|| (Diagonale(pos) == Joueur.MAX))
			return 1;
		else if ((Ligne(pos) == Joueur.MIN) || (Colonne(pos) == Joueur.MIN)
				|| (Diagonale(pos) == Joueur.MIN))
			return -1;
		else
			return 0;
	}

	/**
	 * Méthode qui teste si une grille est pleine
	 * 
	 * @param pos
	 *            Tableau de positions
	 * @return boolean Retourne true si la grille est pleine
	 * @since 1.2a
	 */
	public boolean Pleine(int[][] pos) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (pos[i][j] == Joueur.VIDE)
					return false;

		return true;
	}

	/**
	 * Méthode qui teste si ce tableau de position est un tableau final
	 * 
	 * @param pos
	 *            Tableau de positions
	 * @return boolean Retourne true si la grille est gagné par un joueur
	 * @since 1.2a
	 */
	public boolean PosFinal(int[][] pos) {
		if ((Eval(pos) != 0) || ((Eval(pos) == 0) && (Pleine(pos))))
			return true;
		else
			return false;
	}

	/**
	 * Retourne la categorie d'une joueur
	 * 
	 * @return int Retourne toujours Joueur.AI
	 * @since 1.2a
	 */
	public int getCategorie() {
		return AI;
	}

	/**
	 * Retourne la profondeur de jeu
	 * 
	 * @return int profondeur de jeu
	 * @since 1.1
	 */
	public int getProfondeur() {
		return profondeur;
	}

	/**
	 * Regle la profondeur de jeu pour cette IA
	 * 
	 * @param prof
	 *            Profondeur de jeu
	 * @since 1.1
	 */
	public void setProfondeur(int prof) {
		profondeur = prof;
	}
}