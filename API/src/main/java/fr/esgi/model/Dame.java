package fr.esgi.model;

/**
 * 
 * @author CŽdric TESNIERE
 */
public class Dame extends Piece implements PieceRule {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Constructor without field
	 */
	public Dame() {
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
		return !_pos.equals(getPosition())
				&& (_pos.isSameCol(getPosition())
						|| _pos.isSameRow(getPosition()) || _pos
							.isInDiagonale(getPosition()));
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

}
