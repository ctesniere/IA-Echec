package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente les pions. Le pion est la pièce la moins mobile du
 * jeu et pour cette raison la moins forte. Au début de la partie, chaque joueur
 * possède huit pions, placés en deuxième ligne devant les autres piéces (rangée
 * 2 pour les Blancs et rangée 7 pour les Noirs). Depuis sa position d'origine,
 * le pion peut avancer d'une ou deux cases, au choix du joueur. Par la suite,
 * le pion avance d'une seule case à la fois, sans changer de colonne. Le pion
 * ne peut ni reculer, ni prendre vers l'arrière. Le pion prend en diagonale. Le
 * pion prend une pièce adverse en avançant d'une case en diagonale. La prise
 * n'est pas obligatoire.
 * 
 * @author Cédric TESNIERE
 * @since 1
 */
public class Pion extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Création d'une instance Pion
	 * 
	 * @param color
	 */
	public Pion(int id, boolean color) {
		super(id, color, 100);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	/**
	 * Utilisé quand un échiquier doit être cloné.
	 */
	@Override
	public Object clone() {
		final Pion myClone = new Pion(getId(), isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		return myClone;
	}

	/**
	 * Génère la liste des mouvement posible pour le pion
	 * 
	 * @param chessboard Actuel chessboard
	 * @return Un tableau de tous les coups possibles
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {

		final String positionPiece = chessboard.getPositionPiece(this);
		final int getX = Helper.getXfromString(positionPiece);
		final int getY = Helper.getYfromString(positionPiece);
		int toX = getX, toY = getY;

		// Avance d'une case
		toY += directionMovementY();
		final ArrayList<Move> moves = new ArrayList<Move>();
		Move move = checkThis(toX, toY, chessboard, false);

		// Si aucune piece au nouvelle position du pion
		if (move != null) {
			moves.add(move);

			// Deuxieme deplacement si le pion est toujours à sa position
			// initial
			if ((!Helper.isColorWhite(isColor()) && getY == 6)
					|| (Helper.isColorWhite(isColor()) && getY == 1)) {
				toY += directionMovementY(); // Avance d'une deuxieme case
				move = checkThis(toX, toY, chessboard, false);

				if (move != null) {
					moves.add(move);
				}

			}
		}

		for (int lenght = -1; lenght < 2; lenght++) {
			if (lenght != 0) {
				toX = getX + lenght; // -1 ou 1
				toY = getY + directionMovementY(); // 1 ou 2

				move = checkThis(toX, toY, chessboard, true);

				if (move != null) {
					moves.add(move);
				}
			}
		}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
