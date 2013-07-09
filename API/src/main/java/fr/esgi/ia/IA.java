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
	
	public IA() {

	}

	public void play() {

		boolean aColor = this.black;

		Algorithm anAlgorithm = new AlphaBeta(3);

		setGlobalChessboard(new Chessboard());
		setAlgorithm(anAlgorithm);

		setMyColor(aColor);
		setEnemyColor(!aColor);

		// Boucle principale
		while (true) {

			// waiting for xboard
			String inputCommunication = Helper.readFromInput();

			if ((inputCommunication.charAt(0) == 'u') && (inputCommunication.charAt(1) == 's')) {

				int startX = Helper.getXfromString(inputCommunication.substring(9, 11));
				int startY = Helper.getYfromString(inputCommunication.substring(9, 11));
				int endX = Helper.getXfromString(inputCommunication.substring(11, 13));
				int endY = Helper.getYfromString(inputCommunication.substring(11, 13));

				Move playerMove = new Move(startX, startY, endX, endY,
						isEnemyColor());

				// TODO: Make him hunderstand arrocco

				// For now let's assume that it doesn't do illegal move
				if (playerMove.isValid()) {
					getGlobalChessboard().doMove(playerMove);
				} else {
					System.err.println("ERROR: This move is not valid");
					System.out.println("resign");
					System.exit(0);
				}

//				if (globalChessboard.doMove(playerMove) == false) {
//					System.err.println("ERROR: Illegal move.");
//					continue;
//				}

				/*
				 * TODO if (inCaseOfPromotion != null) miaScacchiera =
				 * Libreria.FaiPromozione(SuaMossa,miaScacchiera,
				 * inCaseOfPromotion);
				 */

				/** MyTurn **/

				Move myMove = anAlgorithm.chooseMove(getGlobalChessboard(), isMyColor());
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

	public boolean isMyColor() {
		return myColor;
	}

	public void setMyColor(boolean _myColor) {
		this.myColor = _myColor;
	}

	public boolean isEnemyColor() {
		return enemyColor;
	}

	public void setEnemyColor(boolean _enemyColor) {
		this.enemyColor = _enemyColor;
	}

	public Chessboard getGlobalChessboard() {
		return globalChessboard;
	}

	public void setGlobalChessboard(Chessboard _globalChessboard) {
		this.globalChessboard = _globalChessboard;
	}

	public Algorithm getAlgorithm() {
		return anAlgorithm;
	}

	public void setAlgorithm(Algorithm _algorithm) {
		this.anAlgorithm = _algorithm;
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean _black) {
		this.black = _black;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean _white) {
		this.white = _white;
	}

}
