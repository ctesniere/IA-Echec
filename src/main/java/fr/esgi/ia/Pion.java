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

		int toX = getX(), toY = getY();

		ArrayList<Move> moves = new ArrayList<Move>();

		// Direction du mouvement
		if (isColorWhite())
			toY++;
		else
			toY--;

		Move move = checkThis(toX, toY, chessboard);

		// Si aucune piece au nouvelle position du pion
		if (move == null) {

			if ((toY == 7) || (toY == 0))
				move.setPromo();

			moves.add(move);

			// Deuxieme deplacement
			if ((!isColorWhite() && getY() != 6) || (isColorWhite() && getY() != 1)) {
				if (isColor())
					toY++;
				else
					toY--;

				move = checkThis(toX, toY, chessboard);

				if (move == null) {
					moves.add(move);
				}

			}
		}

		// Probleme car ces ligne ne seront affecter selement au deuxieme
		// deplacement

		for (int length = 1; length < 3; length++) {

			toX = getX() - 1;

			if (isColorWhite())
				toY = getY() + 1;
			else
				toY = getY() - 1;

			move = checkThis(toX, toY, chessboard);

			if ((move != null) && (move.isColor() != isColor())) {
				if ((toY == 7) || (toY == 0))
					move.setPromo();
				if (move.checkValidity())
					moves.add(move);
			}




			toX = getX() + 1;

			if (isColorWhite())
				toY = getY() + 1;
			else
				toY = getY() - 1;

			move = checkThis(toX, toY, chessboard);

			if ((move != null) && (move.isColor() != isColor())) {
				if ((toY == 7) || (toY == 0))
					move.setPromo();
				if (move.checkValidity())
					moves.add(move);
			}
		}

		return moves;
	}

	@Override
	public Move checkThis(int toX, int toY, Chessboard chessboard) {

		// Vérifie si le déplacement ce trouve sur l'échiquier du Chessboard
		if (Helper.getStringFromPosition(toX, toY) != null) {
			Piece destination = chessboard.getPiece(toX, toY);

			// Si la case est vide
			if (destination == null) {
				Move move = new Move(getX(), getY(), toX, toY, isColor());

				// Si le mouvement est valide, on le retourne
				if (move.checkValidity())
					return move;
			}
		}

		return null;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
