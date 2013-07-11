package fr.esgi.ia;

import java.util.ArrayList;

/**
 * Cette classe represente les pions Le pion est la pièce la moins mobile du jeu
 * et pour cette raison la moins forte. Au début de la partie, chaque joueur
 * possède huit pions, placés en deuxième ligne devant les autres pièces (rangée
 * 2 pour les Blancs et rangée 7 pour les Noirs).
 * 
 * @author Cédric TESNIERE
 */
public class Pion extends Piece {

	/**
	 * Création d'une instance Pion
	 * 
	 * @param _color
	 */
	public Pion(boolean _color) {
		super();
		setColor(isColor());

		if (isColor() == false) setValue(-100);
		else setValue(100);
	}

	/**
	 * Used when a chessboard must be cloned.
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
	 * The Pawn can move until it finds an enemy of an ally piece. He can eat in
	 * other positions. We give all possible moves, not the good ones.
	 * 
	 * @param _chessboard Actuel chessboard
	 * @return An array of all possible moves (not the good ones!)
	 */
	@Override
	public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {

		int toX = getX();
		int toY = getY();

		ArrayList<Move> moves = new ArrayList<Move>();

		// Movement
		if (isColor()) toY++;
		else toY--;

		Piece possiblePiece = _chessboard.getPiece(toX, toY);

		if (possiblePiece == null) {

			Move move = new Move(getX(), getY(), toX, toY, isColor());
			if ((toY == 7) || (toY == 0)) move.SetPromo();
			if (move.isValid()) moves.add(move);

			// Two square
			if (!(isMoved())) {
				if (isColor()) toY++;
				else toY--;

				possiblePiece = _chessboard.getPiece(toX, toY);

				if (possiblePiece == null) {
					move = new Move(getX(), getY(), toX, toY, isColor());
					// move.SetCatturabile();
					if (move.isValid()) moves.add(move);
				}

			}
		}

		// Can i ate?

		toX = getX() - 1;
		// Sx
		if (isColor()) toY = getY() + 1;
		else toY = getY() - 1;

		possiblePiece = _chessboard.getPiece(toX, toY);

		if ((possiblePiece != null) && (possiblePiece.isColor() != isColor())) {

			Move move = new Move(getX(), getY(), toX, toY, isColor());

			if ((toY == 7) || (toY == 0)) move.SetPromo();
			if (move.isValid()) moves.add(move);
		}

		toX = getX() + 1;

		if (isColor()) toY = getY() + 1;
		else toY = getY() - 1;

		possiblePiece = _chessboard.getPieceMouv(toX, toY);

		if ((possiblePiece != null) && (possiblePiece.isColor() != isColor())) {

			Move move = new Move(getX(), getY(), toX, toY, isColor());

			if ((toY == 7) || (toY == 0)) move.SetPromo();
			if (move.isValid()) moves.add(move);
		}

		return moves;
	}
}
