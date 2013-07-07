package fr.esgi.ia.test.model;

/**
 * 
 * @author CŽdric TESNIERE
 */
public class Roi extends Piece implements PieceRule {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Roi() {
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
		/*
		 * try{ // gestion des roques if (couleur.equals(Color.white)){ if
		 * (selfpos.equals(new Position(4, 0)) && ( (pos.equals(new Position(6,
		 * 0)) && c.canPetitRoque()) || (pos.equals(new Position(2,0)) &&
		 * c.canGrandRoque()) ) && !c.CasesMenaced(Color.black, selfpos, pos) &&
		 * !c.inChess(couleur)) return true; }else{ if (selfpos.equals(new
		 * Position(4, 7)) && ( (pos.equals(new Position(6, 7)) &&
		 * c.canPetitRoque()) || (pos.equals(new Position(2,7)) &&
		 * c.canGrandRoque()) ) && !c.CasesMenaced(Color.white, selfpos, pos) &&
		 * !c.inChess(couleur)) return true; } } catch (Exception e){
		 * System.out.println(e); System.exit(0); }
		 */
		return !_pos.equals(getPosition()) && _pos.dx(getPosition()) <= 1
				&& _pos.dy(getPosition()) <= 1;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

}
