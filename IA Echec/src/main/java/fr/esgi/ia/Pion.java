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
	 * Le pion peut se déplacer jusqu'à ce qu'il trouve un ennemi ou une pièce
	 * allié. Il peut manger dans d'autres positions.
	 * 
	 * @param chessboard Actuel chessboard
	 * @return Un tableau de tous les coups possibles (pas les bons!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {

		int toX = getX();
		int toY = getY();

		ArrayList<Move> moves = new ArrayList<Move>();

		// Direction du mouvement
		if (isColor())
			toY++;
		else
			toY--;

		Piece possiblePiece = chessboard.getPiece(toX, toY);

		// Si aucune piece au nouvelle position du pion
		if (possiblePiece == null) {

			Move move = new Move(getX(), getY(), toX, toY, isColor());
			if ((toY == 7) || (toY == 0))
				move.setPromo();
			if (move.isValid())
				moves.add(move);

			// Two square
			if (!(isMoved())) {
				if (isColor())
					toY++;
				else
					toY--;

				possiblePiece = chessboard.getPiece(toX, toY);

				if (possiblePiece == null) {
					move = new Move(getX(), getY(), toX, toY, isColor());
					// move.SetCatturabile();
					if (move.isValid())
						moves.add(move);
				}

			}
		}

		// Can i ate?

		toX = getX() - 1;
		// Sx
		if (isColor())
			toY = getY() + 1;
		else
			toY = getY() - 1;

		possiblePiece = chessboard.getPiece(toX, toY);

		if ((possiblePiece != null) && (possiblePiece.isColor() != isColor())) {

			Move move = new Move(getX(), getY(), toX, toY, isColor());

			if ((toY == 7) || (toY == 0))
				move.setPromo();
			if (move.isValid())
				moves.add(move);
		}

		toX = getX() + 1;

		if (isColor())
			toY = getY() + 1;
		else
			toY = getY() - 1;

		possiblePiece = chessboard.getPieceMouv(toX, toY);

		if ((possiblePiece != null) && (possiblePiece.isColor() != isColor())) {

			Move move = new Move(getX(), getY(), toX, toY, isColor());

			if ((toY == 7) || (toY == 0))
				move.setPromo();
			if (move.isValid())
				moves.add(move);
		}

		return moves;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================
}
