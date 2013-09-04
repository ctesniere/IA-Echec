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

	public MoveEx(int startX, int startY, int endX, int endY, String color) {
		setStartX(startX);
		setEndX(endX);
		setStartY(startY);
		setEndY(endY);
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

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
