package fr.esgi.model;

public interface PieceRule {

	/**
	 * Permet de savoir si un déplacement vers une position définie en parametre est valide
	 * 
	 * @param pos
	 * @return boolean
	 */
	public boolean CanGoTo(Position pos); 
}
