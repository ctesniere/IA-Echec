package fr.esgi.model;

/**
 * 
 * @author CŽdric TESNIERE
 */
public class Tour extends Piece implements PieceRule {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	/**
	 * @see Position
	 * @see Tour#getPosition()
	 * @see Tour#setPosition(Position)
	 */
	private Position position;

	/**
	 * @see Color
	 * @see Tour#getColor()
	 * @see Tour#setColor(Color)
	 */
	private Color color;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Tour() {
		super();
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	@Override
	public boolean CanGoTo(Position _pos) {
		return (!_pos.equals(color) && (_pos.isSameCol(position) || _pos
				.isSameRow(position)));
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position _position) {
		this.position = _position;
	}

	public void setColor(Color _c) {
		color = _c;
	}
}
