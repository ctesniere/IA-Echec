package fr.esgi.model;

/**
 * 
 * @author C�dric TESNIERE
 */
public class Pion extends Piece implements PieceRule {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Pion() {
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
		// if (prise) {
		// // System.out.println("pion prend");
		// return pos.isInDiagonale(selfpos)
		// && (couleur.equals(Color.white) ? (pos.y == selfpos.y + 1)
		// : (pos.y == selfpos.y - 1));
		// } else {
		return _pos.isSameCol(getPosition())
				&& (getColor().equals(Color.white) ? (_pos.y == getPosition().y + 1 || (_pos.y == 3 && getPosition().y == 1))
						: (_pos.y == getPosition().y - 1 || (_pos.y == 4 && getPosition().y == 6)));
		// }
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

}
