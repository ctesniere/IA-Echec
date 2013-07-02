package fr.esgi.model;

public interface PieceRule {

	/**
	 * Permet de savoir si un d�placement vers une position d�finie en parametre est valide
	 * 
	 * @param pos
	 * @return boolean
	 */
	public boolean CanGoTo(Position pos); 
}
