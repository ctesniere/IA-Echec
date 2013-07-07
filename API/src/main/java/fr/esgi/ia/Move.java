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

	// ---------------------------//

	private boolean ismangia = true;

	// Promotion: flag and string

	private boolean promo = false;

	private String promotion = null;

	public Move(int startingX, int startingY, int endingX, int endingY,
			boolean color) {
		startX = startingX;
		endX = endingX;
		startY = startingY;
		endY = endingY;
		setColour(color);
	}

	public boolean checkValidity() {

		// TODO If there is a piece of the same color in this position the move
		// is not valid
		// TODO if there is a king you cannot eat it but is a good move so it's
		// valid

		if (isInBound(startX) && isInBound(startY) && isInBound(endX)
				&& isInBound(endY)) {
			setValid(true);
		} else {
			setValid(false);
		}

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
			if (promo == true) {
				return "move " + cP + cA + getPromotion();
			} else {
				return "move " + cP + cA;
			}
		} else {
			return "Illegal move";
		} // TODO:
	}

	// ----------- OLD GETTER AND SETTER AND FUNC

	public boolean GetMangia() {
		return ismangia;
	}

	public boolean GetPromo() {
		return promo;
	}

	public void SetPromo() {

		if (isColour() == true) {
			promo = true;
		} else {
			setPromotion("q");
		}
	}

	public static Move MossaPromozione(Move move, Chessboard miaScacchiera) {

		// se la mossa mi porta in y=7 o in y=0
		if (((move.isColour()) && (move.getEndY() == 7))
				|| ((!(move.isColour())) && (move.getEndY() == 0))) {

			Piece mioPezzo = miaScacchiera.getPieceMuov(move.getStartX(),
					move.getStartY());
			// se Ã¨ stato un pedone ad eseguire lamossa
			if ((mioPezzo != null) && (mioPezzo.getId() <= 16)) {
				move.SetPromo();
			} // setto la mossa a promozione
		}
		return move;
	}

	public static Chessboard FaiPromozione(Move miaMossa,
			Chessboard miaScacchiera, String promo) {
		// int x=miaMossa.GetXArrivo();
		// int y=miaMossa.GetYArrivo();
		// miaScacchiera.GetQuadrato(x,y)=new Regina();
		return miaScacchiera;
	}

	/*
	 * PRIVATE
	 */

	private boolean isInBound(int value) {

		if ((value >= 0) && (value < 8)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * GETTER AND SETTER
	 */

	public void setPromotion(String promotion) {
		this.promotion = promotion;
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
