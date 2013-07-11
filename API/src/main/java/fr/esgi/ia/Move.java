package fr.esgi.ia;

/**
 * A move
 * 
 * @author CŽdric TESNIERE
 */
public final class Move {

	private int startX;

	private int endX;

	private int startY;

	private int endY;

	private boolean isValid;

	private boolean colour;

	private boolean ismangia = true;

	private boolean promo = false;

	private String promotion = null;

	public Move(int _startingX, int _startingY, int _endingX, int _endingY, boolean _color) {
		startX = _startingX;
		endX = _endingX;
		startY = _startingY;
		endY = _endingY;
		setColour(_color);
	}

	public boolean checkValidity() {

		// TODO If there is a piece of the same color in this position the move
		// is not valid
		// TODO if there is a king you cannot eat it but is a good move so it's
		// valid

		if (isInBound(startX) && isInBound(startY) && isInBound(endX) && isInBound(endY))
			setValid(true);
		else
			setValid(false);

		return true;
	}

	/**
	 * Return the the string representing the move.
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

	public boolean GetMangia() {
		return ismangia;
	}

	public boolean GetPromo() {
		return promo;
	}

	public void SetPromo() {

		if (isColour() == true)
			promo = true;
		else
			setPromotion("q");
	}

	public static Move MossaPromozione(Move _move, Chessboard _miaScacchiera) {

		// se la mossa mi porta in y=7 o in y=0
		if (((_move.isColour()) && (_move.getEndY() == 7))
				|| ((!(_move.isColour())) && (_move.getEndY() == 0))) {

			Piece mioPezzo = _miaScacchiera.getPieceMouv(_move.getStartX(), _move.getStartY());
			// se Ã¨ stato un pedone ad eseguire lamossa
			// if ((mioPezzo != null) && (mioPezzo.getId() <= 16))
			if (true)
				_move.SetPromo();
		}
		return _move;
	}

	public static Chessboard FaiPromozione(Move _miaMossa, Chessboard _miaScacchiera, String _promo) {
		// int x=miaMossa.GetXArrivo();
		// int y=miaMossa.GetYArrivo();
		// miaScacchiera.GetQuadrato(x,y)=new Regina();
		return _miaScacchiera;
	}

	/*
	 * PRIVATE
	 */

	private boolean isInBound(int _value) {

		if ((_value >= 0) && (_value < 8))
			return true;
		else
			return false;
	}

	/*
	 * GETTER AND SETTER
	 */

	public void setPromotion(String _promotion) {
		promotion = _promotion;
	}

	public String getPromotion() {
		return promotion;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setColour(boolean colour) {
		this.colour = colour;
	}

	public boolean isColour() {
		return colour;
	}

}
