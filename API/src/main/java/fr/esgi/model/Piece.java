package fr.esgi.model;

/**
 * Classe mère pour les différentes pièces du plateau
 * 
 * @author Cédric TESNIERE
 */
public class Piece {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	private Position position;

	private Color color;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Piece() {

	}

	public Piece(Position _p) {
		this.position = _p;
		this.color = Color.undef;
	}

	public Piece(Position _p, Color _c) {
		this.position = _p;
		this.color = _c;
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	public char GetRepresentation() {
		if (!color.equals(Color.undef))
			return (char) ((color.equals(Color.white) ? 'A' - 'a' : '\0'));
		else
			return '_';
	}

	public boolean isEmpty() {
		return color.equals(Color.undef);
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position _position) {
		this.position = _position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color _color) {
		this.color = _color;
	}
}
