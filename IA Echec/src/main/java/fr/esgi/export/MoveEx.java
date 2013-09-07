package fr.esgi.export;

import fr.esgi.ia.Helper;

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

	public MoveEx(String pieceNAme, int startX, int startY, int endX, int endY, String color) {
		setPieceName(pieceNAme);
		setStart(Helper.getStringFromPosition(startX, startY));
		setEnd(Helper.getStringFromPosition(endX, endY));
		setColor(color);
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

	public String getPieceName() {
		return pieceName;
	}

	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
