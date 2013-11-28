package fr.esgi.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Cette classe represente l'échiquier.
 * 
 * L'échiquier est le tablier ou plateau du jeu d'échecs. C'est une grille
 * carrée de 8 cases de côté, soit 64 cases en tout, en alternance sombres
 * (appelées noires) et claires (appelées blanches). Un échiquier est intégré à
 * la surface supérieure des tables d'échecs. Dans une partie d'échecs, on
 * dispose l'échiquier de façon à ce que, pour chacun des adversaires, la case
 * du coin gauche (le plus proche) de l'échiquier soit noire. De plus, mais ce
 * n'est pas une obligation, si l'échiquier est doté d'un repère cartésien, les
 * Blancs placent leur pèces sur la rangée 1, leurs pions sur la rangée 2,
 * tandis que Noirs disposent leurs pièces sur la rangée 8, et leurs pions sur
 * la rangée 7. Dans les diagrammes, les Blancs sont toujours représentés en
 * bas, les Noirs en haut.
 * 
 * @author Cédric TESNIERE
 */
public class Chessboard implements Cloneable {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// Nombre de coups possibles. Utilisé pour calculer la mobilité
	private int nbWhiteMoves, nbBlackMoves;

	private Piece[][] chessboard;

	private List<Piece> blacks;

	private List<Piece> whites;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Chessboard() {
		setChessboard(new Piece[8][8]);
		setNbBlackMoves(0);
		setNbWhiteMoves(0);
		initListPieceBalckAndWhite();
	}

	/**
	 * Utilisée par la méthode clone()
	 * 
	 * @param nbWhite
	 * @param nbBlack
	 */
	private Chessboard(int nbWhite, int nbBlack) {
		this();
		setNbBlackMoves(nbBlack); // 0
		setNbWhiteMoves(nbWhite); // 0
	}

	// =========================================================================
	// METHODS
	// =========================================================================

	/**
	 * Initialise les deux array liste pour les noir et blanc
	 */
	public void initListPieceBalckAndWhite() {

		setBlacks(new ArrayList<Piece>());
		setWhites(new ArrayList<Piece>());

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++)
				if (chessboard[y][x] != null)
					if (chessboard[y][x].isColor()) {
						whites.add(chessboard[y][x]);
					} else {
						blacks.add(chessboard[y][x]);
					}
		}
	}

	/**
	 * Retourne un id generer
	 * 
	 * @return Un ID (int) unique dans l'echiquier
	 */
	public int generateID() {

		int min = 1, max = 40;
		int randomNumber;

		// Continue jusqu'a trouver un nombre unique
		while (true) {
			boolean numberExist = false;

			// Genére un nombre aleatoire
			Random rand = new Random();
			randomNumber = rand.nextInt(max - min + 1) + min;

			// Vérifie que le nombre aleatoire generer n'est pas deja utiliser
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (chessboard[y][x] != null && chessboard[y][x].getId() == randomNumber) {
						numberExist = true;
					}
				}
			}

			if (!numberExist)
				break;
		}

		return randomNumber;
	}

	/**
	 * Appliquer un mouvement de l'échiquier.
	 * 
	 * @param thisMove
	 * @return Retourne faux dans un cas d'erreurs
	 */
	public void doMove(Move thisMove) {

		Piece piece = getPiece(thisMove.getStartX(), thisMove.getStartY());

		/* Supprime l'etat de danger */
		// TODO VERIFIER SI CES PIECES PEUVENT ETRE EN DANGER PAR UNE AUTRE DE
		// MES PIECES

		// Générer tous les coups possibles pour cette pièce
		ArrayList<Move> moves = piece.generateMovesForThisPiece(this);

		// Pour chaque mouvement avant le deplacement, trouver si elle mange
		// quelque chose et la mettre en etat de hors de danger
		for (Move move : moves) {
			Piece enemyPiece = chessboard[move.getEndY()][move.getEndX()];
			if ((enemyPiece != null) && (enemyPiece.isColor() != piece.isColor())) {
				enemyPiece.noMoreInDanger();
			}
		}

		// Supprime la pièce qui va ce faire manger de la liste des pieces
		// (blacks/whites) de sa couleur
		if (chessboard[thisMove.getEndY()][thisMove.getEndX()] != null) {
			if (Helper.isColorWhite(piece.isColor()))
				blacks.remove(chessboard[thisMove.getEndY()][thisMove.getEndX()]);
			else
				whites.remove(chessboard[thisMove.getEndY()][thisMove.getEndX()]);
		}

		// Déplacement de la pièce
		chessboard[thisMove.getEndY()][thisMove.getEndX()] = piece;
		chessboard[thisMove.getStartY()][thisMove.getStartX()] = null;

		// Modifie l'etat moved de la pièce
		piece.setMoved(true);

		/* Trouvez si maintenant il ya des morceaux en danger */

		// Générer tous les coups possibles pour cette pièce
		moves = piece.generateMovesForThisPiece(this);

		// For each move find if it eats something
		for (Move move : moves) {
			Piece enemyPiece = chessboard[move.getEndY()][move.getEndX()];
			if ((enemyPiece != null) && (enemyPiece.isColor() != piece.isColor())) {
				enemyPiece.inDanger();
			}
		}
	}

	/**
	 * Retourne la piece de la position x/y
	 * 
	 * @param x
	 * @param y
	 * @return La piece de la position x/y
	 */
	public Piece getPiece(int x, int y) {
		try {
			return chessboard[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Retourne un tableau de tous les mouvements posible pour la couleur
	 * 
	 * @param color
	 * @return Un tableau de toutes les mouvements
	 */
	public ArrayList<Move> generateAllPossibleMoves(boolean color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		List<Piece> listPiece;

		// Sélectionne la liste des pièces selon la couleur
		if (Helper.isColorWhite(color)) {
			listPiece = whites;
		} else {
			listPiece = blacks;
		}

		// Parcours les pièces afin de lister les mouvements possibles
		boolean existRoi = false;
		for (Piece piece : listPiece) {
			if (piece.getClass().equals(Roi.class)) {
				existRoi = true;
			}
			ArrayList<Move> movesForThisPiece = piece.generateMovesForThisPiece(this);
			if (!(movesForThisPiece.isEmpty())) {
				for (Move move : movesForThisPiece) {
					moves.add(move);
				}
			}
		}

		// Si il n'y a plus de roi alors la partie s'arrete
		if (!existRoi)
			moves = new ArrayList<Move>();

		if (Helper.isColorWhite(color)) {
			setNbWhiteMoves(moves.size());
		} else {
			setNbBlackMoves(moves.size());
		}

		return moves;
	}

	/**
	 * Insert les pièces dans l'echiquier
	 * 
	 * @param color
	 * @param king
	 * @param queen
	 * @param crazy
	 * @param _knight
	 * @param _tower
	 * @param _pawn
	 * @return
	 */
	public void insertPieceChessboard(boolean color, String king, String queen, String crazy,
			String knight, String tower, String pawn) {

		String[] listLocation;
		int y, x;

		// Traitement pour le roi
		if (!king.equals("null")) {
			listLocation = king.split(":");
			for (int i = 0; i < listLocation.length; i++) {
				y = Helper.getYfromString(listLocation[i]);
				x = Helper.getXfromString(listLocation[i]);
				if (Helper.getStringFromPosition(x, y) != null)
					chessboard[y][x] = new Roi(generateID(), color);
			}
		}

		// Traitement pour la reine
		if (!queen.equals("null")) {
			listLocation = queen.split(":");
			for (int i = 0; i < listLocation.length; i++) {
				y = Helper.getYfromString(listLocation[i]);
				x = Helper.getXfromString(listLocation[i]);
				if (Helper.getStringFromPosition(x, y) != null)
					chessboard[y][x] = new Reine(generateID(), color);
			}
		}

		// Traitement pour le fou
		if (!crazy.equals("null")) {
			listLocation = crazy.split(":");
			for (int i = 0; i < listLocation.length; i++) {
				y = Helper.getYfromString(listLocation[i]);
				x = Helper.getXfromString(listLocation[i]);
				if (Helper.getStringFromPosition(x, y) != null)
					chessboard[y][x] = new Fou(generateID(), color);
			}
		}

		// Traitement pour le cavalier
		if (!knight.equals("null")) {
			listLocation = knight.split(":");
			for (int i = 0; i < listLocation.length; i++) {
				y = Helper.getYfromString(listLocation[i]);
				x = Helper.getXfromString(listLocation[i]);
				if (Helper.getStringFromPosition(x, y) != null)
					chessboard[y][x] = new Chevalier(generateID(), color);
			}
		}

		// Traitement pour la tour
		if (!tower.equals("null")) {
			listLocation = tower.split(":");
			for (int i = 0; i < listLocation.length; i++) {
				y = Helper.getYfromString(listLocation[i]);
				x = Helper.getXfromString(listLocation[i]);
				if (Helper.getStringFromPosition(x, y) != null)
					chessboard[y][x] = new Tour(generateID(), color);
			}
		}

		// Traitement pour le pion
		if (!pawn.equals("null")) {
			listLocation = pawn.split(":");
			for (int i = 0; i < listLocation.length; i++) {
				y = Helper.getYfromString(listLocation[i]);
				x = Helper.getXfromString(listLocation[i]);
				if (Helper.getStringFromPosition(x, y) != null)
					chessboard[y][x] = new Pion(generateID(), color);
			}
		}
	}

	public String getPositionPiece(Piece piece) {

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (chessboard[y][x] != null) {
					if (chessboard[y][x].getClass().equals(piece.getClass())
							&& chessboard[y][x].isColor() == piece.isColor()
							&& chessboard[y][x].getId() == piece.getId()) {
						return Helper.getStringFromPosition(x, y);
					}
				}
			}
		}

		return null;
	}

	// =========================================================================
	// OVERRIDES
	// =========================================================================

	@Override
	public Object clone() {
		Chessboard chessboardClone = new Chessboard(getNbWhiteMoves(), getNbBlackMoves());

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++)
				if (chessboard[y][x] != null) {
					Piece pieceClone = (Piece) chessboard[y][x].clone();
					chessboardClone.chessboard[y][x] = pieceClone;
					if (Helper.isColorWhite(pieceClone.isColor())) {
						chessboardClone.whites.add(pieceClone);
					} else {
						chessboardClone.blacks.add(pieceClone);
					}
				} else {
					chessboardClone.chessboard[y][x] = null;
				}
		}
		return chessboardClone;
	}

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public int getNbWhiteMoves() {
		return nbWhiteMoves;
	}

	public void setNbWhiteMoves(int nbWhiteMoves) {
		this.nbWhiteMoves = nbWhiteMoves;
	}

	public int getNbBlackMoves() {
		return nbBlackMoves;
	}

	public void setNbBlackMoves(int nbBlackMoves) {
		this.nbBlackMoves = nbBlackMoves;
	}

	public Piece[][] getChessboard() {
		return chessboard;
	}

	public void setChessboard(Piece[][] chessboard) {
		this.chessboard = chessboard;
	}

	public List<Piece> getBlacks() {
		return blacks;
	}

	public void setBlacks(List<Piece> blacks) {
		this.blacks = blacks;
	}

	public List<Piece> getWhites() {
		return whites;
	}

	public void setWhites(List<Piece> whites) {
		this.whites = whites;
	}
}
