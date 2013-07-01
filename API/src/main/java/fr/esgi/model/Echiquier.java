package fr.esgi.model;

/**
 * Cette classe g�re le placement des pi�ces sur l'echiquier
 * 
 * @author C�dric TESNIERE
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
