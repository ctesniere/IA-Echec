package fr.esgi.export;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.ia.Chessboard;
import fr.esgi.ia.Helper;
import fr.esgi.ia.Piece;

public class Export {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private List<PieceEx> listPiece = new ArrayList<PieceEx>();

	private PieceEx bestMovePiece;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Export(Chessboard chessboard) {

		Piece piece;
		PieceEx pieceEx;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				piece = chessboard.getPiece(x, y);
				if (piece != null) {
					pieceEx = new PieceEx();
					pieceEx.setLocation(Helper.getStringFromPosition(x, y));
					pieceEx.setName(piece.getClass().getSimpleName());
					// pieceEx.setColor(piece.getColor()); // Probleme
					listPiece.add(pieceEx);
				}
			}
		}
	}

	// =========================================================================
	// METHODS
	// =========================================================================

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

	public PieceEx getBestMovePiece() {
		return bestMovePiece;
	}

	public void setBestMovePiece(PieceEx bestMovePiece) {
		this.bestMovePiece = bestMovePiece;
	}

}
