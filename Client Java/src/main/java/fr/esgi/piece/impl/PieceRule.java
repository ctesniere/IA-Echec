package fr.esgi.piece.impl;

import fr.esgi.service.Color;
import fr.esgi.service.Position;

/**
 * PieceRule
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public interface PieceRule {
	public void setColor(Color c);

	public void setPosition(Position p);

	public void setPosition(Position p, boolean b);

	public String toString();
}
