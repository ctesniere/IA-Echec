package fr.esgi.export;

import fr.esgi.ia.Helper;

/**
 * Classe pour le meilleur mouvement à exporter
 * 
 * @author Cédric TESNIERE
 */
public final class MoveEx {

	private String start;

	private String end;

	private String color;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public MoveEx(int startX, int startY, int endX, int endY, String color) {
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
