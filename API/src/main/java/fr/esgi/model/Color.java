package fr.esgi.model;

/**
 * 
 * @author CŽdric TESNIERE
 */
public enum Color {
	white, black, undef;

	public boolean equals(Color _c) {
		return this == _c;
	}

	public Color changeColor() {
		if (this == Color.white) {
			return Color.black;
		} else if (this == Color.black) {
			return Color.white;
		} else {
			return Color.undef;
		}
	}
}
