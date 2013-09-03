package fr.esgi.export;

import java.util.ArrayList;
import java.util.List;

public class Export {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private List<PieceEx> listPiece = new ArrayList<PieceEx>();

	private PieceEx bestMovePiece;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Export() {

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
