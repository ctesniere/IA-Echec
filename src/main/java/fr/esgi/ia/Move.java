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
	 * Retourne une chaine représentant le déplacement
	 * 
	 * @return The string representing the move
	 */
	public String moveOutputString() {

		String positionStart;
		String positionEnd;

		positionStart = Helper.getStringFromPosition(startX, startY);
		positionEnd = Helper.getStringFromPosition(endX, endY);

		if (isValid()) {
			if (promo == true)
				return "move " + positionStart + positionEnd + getPromotion();
			else
				return "move " + positionStart + positionEnd;
		} else
			return "Illegal move";
	}

	public void setPromo() {

		if (isColor() == true)
			promo = true;
		else
			setPromotion("q");
	}

	public static Move movePromotion(Move move, Chessboard chessboard) {

		// Si le mouvement me faut pour y = 7 ou y = 0
		if (((move.isColor()) && (move.getEndY() == 7))
				|| ((!(move.isColor())) && (move.getEndY() == 0))) {

			Piece piece = chessboard.getPieceMouv(move.getStartX(), move.getStartY());
			
			// si c'était un gage de faire un geste
			if ((piece != null) && (piece.getName() != Pion.class.getSimpleName()))
				move.setPromo();
		}
		return move;
	}

	public static Chessboard doPromotion(Move move, Chessboard chessboard, String promo) {
		// int x=miaMossa.GetXArrivo();
		// int y=miaMossa.GetYArrivo();
		// miaScacchiera.GetQuadrato(x,y)=new Regina();
		return chessboard;
	}



	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================


	public boolean getMangia() {
		return isEating;
	}

	public boolean getPromo() {
		return promo;
	}

	private boolean isInBound(int value) {

		if ((value >= 0) && (value < 8))
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
