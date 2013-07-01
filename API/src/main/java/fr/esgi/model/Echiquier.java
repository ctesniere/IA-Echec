package fr.esgi.model;

/**
 * Cette classe gère le placement des piéces sur l'echiquier
 * 
 * @author Cédric TESNIERE
 */
public class Echiquier {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

	private Piece echiquier[];
	
	public Echiquier() {

	}

	public Echiquier(Piece[] echiquier) {
		super();
		this.echiquier = echiquier;
	}

	public Piece[] getEchiquier() {
		return echiquier;
	}

	public void setEchiquier(Piece[] echiquier) {
		this.echiquier = echiquier;
	}
	
	
}
