package fr.esgi.service;

/**
 * Color
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public enum Color {
	white, black, undef;

	public boolean equals(Color c) {
		return this == c;
	}

	public Color oltherColor() {
		if (this == Color.white) {
			return Color.black;
		} else if (this == Color.black) {
			return Color.white;
		} else {
			return Color.undef;
		}
	}
}
