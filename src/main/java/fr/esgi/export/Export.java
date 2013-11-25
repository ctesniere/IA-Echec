package fr.esgi.export;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.ia.Chessboard;
import fr.esgi.ia.Helper;
import fr.esgi.ia.Move;
import fr.esgi.ia.Piece;

public class Export {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private List<PieceEx> listPiece = new ArrayList<PieceEx>();

	private MoveEx bestMovePiece;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Export(Chessboard chessboard, Move move) {

		// Ajout dans la liste des pièces
		Piece piece;
		PieceEx pieceEx;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				piece = chessboard.getPiece(x, y);
				if (piece != null) {
					pieceEx = new PieceEx();
					pieceEx.setLocation(Helper.getStringFromPosition(x, y));
					pieceEx.setName(piece.getClass().getSimpleName());
					pieceEx.setColor(getColorInString(piece.isColor()));
					listPiece.add(pieceEx);
				}
			}
		}

		// Ajout du meilleur coups
		bestMovePiece = new MoveEx(move.getPieceName(), move.getStartX(), move.getStartY(),
				move.getEndX(), move.getEndY(), getColorInString(move.isColor()));
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	public String getColorInString(boolean color) {
		if (Helper.isColorWhite(color))
			return "White";
		else
			return "Black";
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public List<PieceEx> getListPiece() {
		return listPiece;
	}

	public void setListPiece(List<PieceEx> listPiece) {
		this.listPiece = listPiece;
	}

	public MoveEx getBestMovePiece() {
		return bestMovePiece;
	}

	public void setBestMovePiece(MoveEx bestMovePiece) {
		this.bestMovePiece = bestMovePiece;
	}

}
