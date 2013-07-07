package fr.esgi.ia.test.model;

/**
 * 
 * @author CŽdric TESNIERE
 */
public class Position {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	public int x, y;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Position(Position _p) {
		this.x = _p.x;
		this.x = _p.y;
	}

	public Position(int _x, int _y) {
		this.x = _x;
		this.x = _y;
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * Retourne true si la colone est identique au parametre
	 * 
	 * @param _p
	 * @return boolean
	 */
	public boolean isSameCol(Position _p) {
		return (x == _p.x);
	}

	/**
	 * Retourne true si la ligne est identique au parametre
	 * 
	 * @param _p
	 * @return boolean
	 */
	public boolean isSameRow(Position _p) {
		return (y == _p.y);
	}

	public int d(int _a, int _b) {
		return Math.abs(_a - _b);
	}

	public int dx(Position _p) {
		return d(x, _p.x);
	}

	public int dy(Position _p) {
		return d(y, _p.y);
	}

	public boolean isInDiagonale(Position _p) {
		return d(_p.x, x) == d(_p.y, y);
	}

	public boolean equals(Position _p) {
		return (_p.x == x && _p.y == y);
	}

	public boolean equals(int _px, int _py) {
		return (_px == x && _py == y);
	}

	public void toWalk(Position _p) {
		if (_p.x < x) {
			x--;
		} else if (_p.x > x) {
			x++;
		}

		if (_p.y < y) {
			y--;
		} else if (_p.y > y) {
			y++;
		}
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public int getX() {
		return x;
	}

	public void setX(int _x) {
		this.x = _x;
	}

	public int getY() {
		return y;
	}

	public void setY(int _y) {
		this.y = _y;
	}
}
