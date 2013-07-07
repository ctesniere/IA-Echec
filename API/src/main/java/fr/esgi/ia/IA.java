package fr.esgi.ia;


/**
 * @author CŽdric TESNIERE
 */
public class IA {

	private boolean myColor;

	private boolean enemyColor;

	private Chessboard globalChessboard;

	private Algorithm anAlgorithm;

	private boolean black = false;

	private boolean white = true;

	public void play() {

		boolean aColor = this.black;

		Algorithm anAlgorithm = new AlphaBeta(3);

		this.setGlobalChessboard(new Chessboard());
		this.setAlgorithm(anAlgorithm);

		this.setMyColor(aColor);
		this.setEnemyColor(!aColor);

		// Boucle principale
		while (true) {

			/** User turn **/

			// waiting for xboard
			String inputCommunication = Helper.readFromInput();

			if (inputCommunication.equalsIgnoreCase("XBoard")) {
				// communication protocol
				String output = "feature done=0 ping=0 usermove=1 time=0 draw=0 reuse=0 "
						+ "analyze=0 myname=\"TiAroDiBruttoRevenge\" variants=\"normal\" colors=0 "
						+ "sigint=0 sigterm=0 done=1";
				System.out.println(output);
			}

			if ((inputCommunication.charAt(0) == 'u') && (inputCommunication.charAt(1) == 's')) {

				/*
				 * String inCaseOfPromotion = null; try { inCaseOfPromotion =
				 * comunicazioneIn.substring(14); } catch
				 * (StringIndexOutOfBoundsException e) { inCaseOfPromotion =
				 * null; }
				 */

				// TODO: Use charAt??
				int startX = Helper.getXfromString(inputCommunication
						.substring(9, 11));
				int startY = Helper.getYfromString(inputCommunication
						.substring(9, 11));
				int endX = Helper.getXfromString(inputCommunication.substring(
						11, 13));
				int endY = Helper.getYfromString(inputCommunication.substring(
						11, 13));

				Move playerMove = new Move(startX, startY, endX, endY,
						isEnemyColor());

				/*
				 * TODO: Flag playerMove = Move.MossaPromozione (playerMove,
				 * globalChessboard); playerMove = Move.MossaCatturabile
				 * (playerMove); playerMove = Move.MossaCatturante (playerMove,
				 * globalChessboard); playerMove = Move.MossaArrocco
				 * (playerMove, globalChessboard);
				 */

				// TODO: Make him hunderstand arrocco

				// For now let's assume that it doesn't do illegal move
				if (playerMove.isValid()) {
					getGlobalChessboard().doMove(playerMove);
				} else {
					System.err.println("ERROR: This move is not valid");
					System.out.println("resign");
					System.exit(0);
				}

				/*
				 * if (globalChessboard.doMove(playerMove) == 0) {
				 * System.err.println("ERROR: Illegal move."); continue; }
				 */
				/*
				 * TODO if (inCaseOfPromotion != null) miaScacchiera =
				 * Libreria.FaiPromozione(SuaMossa,miaScacchiera,
				 * inCaseOfPromotion);
				 */

				/** MyTurn **/

				Move myMove = anAlgorithm.chooseMove(getGlobalChessboard(),
						isMyColor());
				if (myMove == null) {
					System.err.println("ERROR: No possible move.");
					System.out.println("resign");
					System.exit(0);
				} else {
					getGlobalChessboard().doMove(myMove); // TODO: Check return
															// value?
					System.out.println(myMove.moveOutputString());
				}
			}
		}
	}

	/**
	 * @return the myColor
	 */
	public boolean isMyColor() {
		return myColor;
	}

	/**
	 * @param _myColor
	 *            the myColor to set
	 */
	public void setMyColor(boolean _myColor) {
		this.myColor = _myColor;
	}

	/**
	 * @return the enemyColor
	 */
	public boolean isEnemyColor() {
		return enemyColor;
	}

	/**
	 * @param _enemyColor
	 *            the enemyColor to set
	 */
	public void setEnemyColor(boolean _enemyColor) {
		this.enemyColor = _enemyColor;
	}

	/**
	 * @return the globalChessboard
	 */
	public Chessboard getGlobalChessboard() {
		return globalChessboard;
	}

	/**
	 * @param _globalChessboard
	 *            the globalChessboard to set
	 */
	public void setGlobalChessboard(Chessboard _globalChessboard) {
		this.globalChessboard = _globalChessboard;
	}

	/**
	 * @return the algorithm
	 */
	public Algorithm getAlgorithm() {
		return anAlgorithm;
	}

	/**
	 * @param _algorithm
	 *            the algorithm to set
	 */
	public void setAlgorithm(Algorithm _algorithm) {
		this.anAlgorithm = _algorithm;
	}

	/**
	 * @return the black
	 */
	public boolean isBlack() {
		return black;
	}

	/**
	 * @param _black
	 *            the black to set
	 */
	public void setBlack(boolean _black) {
		this.black = _black;
	}

	/**
	 * @return the white
	 */
	public boolean isWhite() {
		return white;
	}

	/**
	 * @param _white
	 *            the white to set
	 */
	public void setWhite(boolean _white) {
		this.white = _white;
	}

}
