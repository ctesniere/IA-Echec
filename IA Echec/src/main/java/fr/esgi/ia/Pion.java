package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente les pions Le pion est la pi�ce la moins mobile du jeu
 * et pour cette raison la moins forte. Au d�but de la partie, chaque joueur
 * poss�de huit pions, plac�s en deuxi�me ligne devant les autres pi�ces (rang�e
 * 2 pour les Blancs et rang�e 7 pour les Noirs).
 * 
 * @author C�dric TESNIERE
 * @since 1
 */
public class Pion extends Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	/**
	 * Cr�ation d'une instance Pion
	 * 
	 * @param color
	 */
	public Pion(boolean color) {
		super();
		setName(this.getClass().getSimpleName());
		setColor(isColor());

		if (isColor() == false)
			setValue(-100);
		else
			setValue(100);
	}

	/**
	 * Utilis� quand un �chiquier doit �tre clon�.
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
	 * Le pion peut se d�placer jusqu'� ce qu'il trouve un ennemi ou une pi�ce
	 * alli�. Il peut manger dans d'autres positions. Nous donnons tous les
	 * coups possibles, pas les bons.
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
}
