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
	public Pion(boolean color) {
		super(color, 100);
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
		Pion myClone = new Pion(isColor());
		myClone.setInDanger(isInDanger());
		myClone.setEnemy(getEnemy());
		myClone.setMoved(isMoved());
		myClone.setPosition(getX(), getY());
		myClone.setValPos(getValPos());
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

		int toX = getX();
		int toY = getY();
		int nbCaseLimite = 1;
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move = checkThis(toX, toY, chessboard);

		// Avance d'une case
		toY += directionMovementY();

		// Si aucune piece au nouvelle position du pion
		if (move == null) {
			moves.add(move);

			// Deuxieme deplacement si le pion est toujours à sa position
			// initial
			if ((!Helper.isColorWhite(isColor()) && getY() == 6) || (Helper.isColorWhite(isColor()) && getY() == 1)) {
				nbCaseLimite++;
				toY += directionMovementY(); // Avance d'une deuxieme case
				move = checkThis(toX, toY, chessboard);

				if (move == null) {
					moves.add(move);
				}

			}
		}

		// Probleme car ces ligne ne seront affecter seulement au deuxieme
		// deplacement

		for (int lenght = -1; lenght < 2; lenght++) {
			for (int nbCase = 1; nbCase <= nbCaseLimite; nbCase++) {
				if (lenght != 0) {
					toX = getX() + lenght; // -1 ou 1
					toY = getY() + (directionMovementY() * nbCase); // 1 ou 2

					move = checkThis(toX, toY, chessboard);

					if ((move != null) && (move.isColor() != isColor())) {
						if (move.checkValidity())
							moves.add(move);
					}
				}
			}
		}

		return moves;
	}

	@Override
	public Move checkThis(int toX, int toY, Chessboard chessboard) {

		Piece destination = chessboard.getPiece(toX, toY);

		// Si c'est une pièce enemie ou si la case est vide
		if (destination == null) {
			Move move = new Move(getX(), getY(), toX, toY, isColor());

			// Si le mouvement est valide, on le retourne
			if (move.checkValidity())
				return move;
		}

		return null;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
