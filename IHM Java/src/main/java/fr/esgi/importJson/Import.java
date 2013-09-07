package fr.esgi.importJson;

import java.util.ArrayList;
import java.util.List;

public class Import {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private List<PieceEx> listPiece = new ArrayList<PieceEx>();

	private MoveEx bestMovePiece;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Import() {

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

	public MoveEx getBestMovePiece() {
		return bestMovePiece;
	}

}
