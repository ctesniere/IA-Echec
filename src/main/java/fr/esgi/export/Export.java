package fr.esgi.export;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.ia.Chessboard;

public class Export {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private List<PieceEx> listPiece = new ArrayList<PieceEx>();

	private PieceEx chooseMovePiece;

	private PieceEx bestMovePiece;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Export(Chessboard _chessboard) {
		super();
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

	public PieceEx getChooseMovePiece() {
		return chooseMovePiece;
	}

	public void setChooseMovePiece(PieceEx chooseMovePiece) {
		this.chooseMovePiece = chooseMovePiece;
	}

	public PieceEx getBestMovePiece() {
		return bestMovePiece;
	}

	public void setBestMovePiece(PieceEx bestMovePiece) {
		this.bestMovePiece = bestMovePiece;
	}

}
