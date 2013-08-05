package fr.esgi.ia;

/**
 * A move
 * 
 * @author Cédric TESNIERE
 */
public final class Move {

	private int startX;

	private int endX;

	private int startY;

	private int endY;

	private boolean isValid;

	private boolean color;

	private boolean isEating = true;

	private boolean promo = false;

	private String promotion = null;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Move(int startX, int startY, int endX, int endY, boolean color) {
		setStartX(startX);
		setEndX(endX);
		setStartY(startY);
		setEndY(endY);
		setColor(color);
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	public boolean checkValidity() {

		if (isInBound(startX) && isInBound(startY) && isInBound(endX) && isInBound(endY))
			setValid(true);
		else
			setValid(false);

		return true;
	}

	/**
	 * Retourne le string représentant le déplacement
	 * 
	 * @return The string representing the move
	 */
	public String moveOutputString() {

		String cP;
		String cA;

		cP = Helper.getStringFromPosition(startX, startY);
		cA = Helper.getStringFromPosition(endX, endY);

		if (isValid()) {
			if (promo == true)
				return "move " + cP + cA + getPromotion();
			else
				return "move " + cP + cA;
		} else
			return "Illegal move";
	}

	public boolean getMangia() {
		return isEating;
	}

	public boolean getPromo() {
		return promo;
	}

	public void setPromo() {

		if (isColor() == true)
			promo = true;
		else
			setPromotion("q");
	}

	public static Move mossaPromozione(Move _move, Chessboard _miaScacchiera) {

		// Si le mouvement me faut pour y = 7 ou y = 0
		if (((_move.isColor()) && (_move.getEndY() == 7))
				|| ((!(_move.isColor())) && (_move.getEndY() == 0))) {

			Piece mioPezzo = _miaScacchiera.getPieceMouv(_move.getStartX(), _move.getStartY());
			// si c'�tait un gage de faire un geste
			// if ((mioPezzo != null) && (mioPezzo.getId() <= 16))
			if (true)
				_move.setPromo();
		}
		return _move;
	}

	public static Chessboard faiPromozione(Move _miaMossa, Chessboard _miaScacchiera, String _promo) {
		// int x=miaMossa.GetXArrivo();
		// int y=miaMossa.GetYArrivo();
		// miaScacchiera.GetQuadrato(x,y)=new Regina();
		return _miaScacchiera;
	}



	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	private boolean isInBound(int _value) {

		if ((_value >= 0) && (_value < 8))
			return true;
		else
			return false;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public boolean isEating() {
		return isEating;
	}

	public void setEating(boolean isEating) {
		this.isEating = isEating;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}
}
