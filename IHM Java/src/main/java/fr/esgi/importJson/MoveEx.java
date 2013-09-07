package fr.esgi.importJson;


/**
 * Classe pour le meilleur mouvement à exporter
 * 
 * @author Cédric TESNIERE
 */
public final class MoveEx {
	
	private String pieceName;

	private String start;

	private String end;

	private String color;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public MoveEx() {

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

	public String getColor() {
		return color;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getPieceName() {
		return pieceName;
	}
}
