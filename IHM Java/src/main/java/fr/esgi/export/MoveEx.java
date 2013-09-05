package fr.esgi.export;

/**
 * Classe pour le meilleur mouvement à exporter
 * 
 * @author Cédric TESNIERE
 */
public final class MoveEx {

	private int startX;

	private int endX;

	private int startY;

	private int endY;

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

	public int getStartX() {
		return startX;
	}

	public int getEndX() {
		return endX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndY() {
		return endY;
	}

	public String getColor() {
		return color;
	}
}
