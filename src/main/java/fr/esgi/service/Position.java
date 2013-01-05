package fr.esgi.service;

/**
 * Position
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public class Position {
	
	public int x, y;

	public Position(int x, int y) {
	
	}

	public void set(int x, int y) {
		
	}

	public boolean isSameCol(Position p) {
		return false;
	}

	public boolean isSameRow(Position p) {
		return false;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public boolean isInDiagonale(Position p) {
		return false;
	}

	public boolean equals(Position p) {
		return p.x == this.x && p.y == this.y;
	}

	public boolean equals(int px, int py) {
		return px == this.x && py == this.y;
	}

	public void go(Position p) {
		
	}

}
