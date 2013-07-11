package fr.esgi.ia;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe represente l'Échiquier.
 * 
 * L'échiquier est le tablier ou plateau du jeu d'échecs. C'est une grille
 * carrée de 8 cases de côté, soit 64 cases en tout, en alternance sombres
 * (appelées noires) et claires (appelées blanches). Un échiquier est intégré à
 * la surface supérieure des tables d'échecs. Dans une partie d'échecs, on
 * dispose l'échiquier de façon à ce que, pour chacun des adversaires, la case
 * du coin gauche (le plus proche) de l'échiquier soit noire. De plus, mais ce
 * n'est pas une obligation, si l'échiquier est doté d'un repère cartésien, les
 * Blancs placent leur pièces sur la rangée 1, leurs pions sur la rangée 2,
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

	// Number of possible moves. Used to calculate mobility.
	private int nWhiteMoves;

	private int nBlackMoves;

	private Piece[][] chessboard;

	private List<Piece> blacks;

	private List<Piece> whites;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public Chessboard() {
		chessboard = new Piece[8][8];

		nBlackMoves = 0;
		nWhiteMoves = 0;

		initListPieceBalckAndWhite();
	}

	/**
	 * Utilisée par la méthode clone()
	 * 
	 * @param _nWhite
	 * @param _nBlack
	 */
	private Chessboard(int _nWhite, int _nBlack) {
		this();
		nWhiteMoves = _nWhite;
		nBlackMoves = _nBlack;
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
				if (chessboard[x][y] != null)
					if (chessboard[x][y].isColor()) {
						whites.add(chessboard[x][y]);
					} else {
						blacks.add(chessboard[x][y]);
					}
		}
	}

	/**
	 * Appliquer un mouvement de l'échiquier.
	 * 
	 * @param _thisMove
	 * @return Retourne faux dans un cas d'erreurs
	 */
	public boolean doMove(Move _thisMove) {

		int fromX, fromY, toX, toY;
		ArrayList<Move> moves;
		Piece piece;

		fromX = _thisMove.getStartX();
		fromY = _thisMove.getStartY();
		toX = _thisMove.getEndX();
		toY = _thisMove.getEndY();

		// Get piece
		piece = getPieceMouv(fromX, fromY);

		/* Remove the inDanger flag if a piece is no more in Danger */

		// Generate all possible moves for this piece
		moves = piece.generateMovesForThisPiece(this);
		// For each move find if it eats something
		for (Move move : moves) {
			Piece enemyPiece = chessboard[move.getEndY()][move.getEndX()];
			if ((enemyPiece != null) && (enemyPiece.isColor() != piece.isColor())) {
				enemyPiece.noMoreInDanger(); // I will no longer set it in
				// danger
			}
		}

		/* Apply changes */

		piece.setPosition(toX, toY);

		if (piece.isColor() && (chessboard[toY][toX] != null)) {
			blacks.remove(chessboard[toY][toX]);
		}
		if (!(piece.isColor()) && (chessboard[toY][toX] != null)) {
			whites.remove(chessboard[toY][toX]);
		}

		chessboard[toY][toX] = piece;
		chessboard[fromY][fromX] = null;

		// I moved
		piece.setMoved(true);

		/* Find if now there are pieces in danger */

		// Generate all possible moves for this piece
		moves = piece.generateMovesForThisPiece(this);

		// For each move find if it eats something
		for (Move move : moves) {
			Piece enemyPiece = chessboard[move.getEndY()][move.getEndX()];
			if ((enemyPiece != null) && (enemyPiece.isColor() != piece.isColor())) {
				enemyPiece.inDanger();
			}
		}

		return (true);
	}

	/**
	 * Retourne la piece de la position x/y
	 * 
	 * @param _x
	 * @param _y
	 * @return La piece de la position x/y
	 */
	public Piece getPieceMouv(int _x, int _y) {
		try {
			return chessboard[_y][_x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Retourne la piece de la position x/y
	 * 
	 * @param _x
	 * @param _y
	 * @return La piece de la position x/y
	 */
	public Piece getPiece(int _x, int _y) {
		try {
			return chessboard[_y][_x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Retourne un tableau de tous les mouvements posible pour la couleur
	 * 
	 * @param _color
	 * @return Un tableau de toutes les mouvements
	 */
	public ArrayList<Move> generateAllPossibleMoves(boolean _color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		List<Piece> temp;

		if (_color) {
			temp = whites;
		} else {
			temp = blacks;
		}

		for (Piece thisPiece : temp) {
			ArrayList<Move> movesForThisPiece = thisPiece.generateMovesForThisPiece(this);
			if (!(movesForThisPiece.isEmpty())) {
				for (Move move : movesForThisPiece) {
					moves.add(move);
				}
			}
		}

		if (_color) {
			setnWhiteMoves(moves.size());
		} else {
			setnBlackMoves(moves.size());
		}

		return moves;
	}

	@Override
	public Object clone() {
		Chessboard chessboardClone = new Chessboard(getnWhiteMoves(), getnBlackMoves());

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++)
				if (chessboard[y][x] != null) {
					Piece pieceClone = (Piece) chessboard[y][x].clone();
					chessboardClone.chessboard[y][x] = pieceClone;
					if (pieceClone.isColor()) {
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

	public List<List<String>> getChessboardForJson() {
		initListPieceBalckAndWhite();

		List<List<String>> listPiece = new ArrayList<List<String>>();
		List<String> listBlack = new ArrayList<String>();
		List<String> listWhite = new ArrayList<String>();
		Piece piece;

		listWhite.add("White");
		listBlack.add("Black");

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++)
				if (chessboard[x][y] != null)
					if (chessboard[x][y].isColor()) {
						piece = chessboard[x][y];
						listWhite.add(piece.getClass().getSimpleName() + ";"
								+ Helper.getStringFromPosition(x, y));
					} else {
						piece = chessboard[x][y];
						listBlack.add(piece.getClass().getSimpleName() + ";"
								+ Helper.getStringFromPosition(x, y));
					}
		}

		listPiece.add(listBlack);
		listPiece.add(listWhite);

		return listPiece;
	}

	/**
	 * Définie l'emplacement de la piece roi
	 * 
	 * @param _location Emplacement sur l'echiquier
	 * @param _color La couleur de la piece
	 */
	public void setPositionKing(String _location, boolean _color) {
		int y = Helper.getYfromString(_location);
		int x = Helper.getXfromString(_location);
		chessboard[y][x] = new Roi(_color);
	}

	/**
	 * Définie l'emplacement de la piece reine
	 * 
	 * @param _location Emplacement sur l'echiquier
	 * @param _color La couleur de la piece
	 */
	public void setPositionQueen(String _location, boolean _color) {
		int y = Helper.getYfromString(_location);
		int x = Helper.getXfromString(_location);
		chessboard[y][x] = new Reine(_color);
	}

	/**
	 * Définie l'emplacement de la piece tour
	 * 
	 * @param _location Emplacement sur l'echiquier
	 * @param _color La couleur de la piece
	 */
	public void setPositionTower(String _location, boolean _color) {
		int y = Helper.getYfromString(_location);
		int x = Helper.getXfromString(_location);
		chessboard[y][x] = new Tour(_color);
	}

	/**
	 * Définie l'emplacement de la piece chevalier
	 * 
	 * @param _location Emplacement sur l'echiquier
	 * @param _color La couleur de la piece
	 */
	public void setPositionKnight(String _location, boolean _color) {
		int y = Helper.getYfromString(_location);
		int x = Helper.getXfromString(_location);
		chessboard[y][x] = new Chevalier(_color);
	}

	/**
	 * Définie l'emplacement de la piece fou
	 * 
	 * @param _location Emplacement sur l'echiquier
	 * @param _color La couleur de la piece
	 */
	public void setPositionCrazy(String _location, boolean _color) {
		int y = Helper.getYfromString(_location);
		int x = Helper.getXfromString(_location);
		chessboard[y][x] = new Fou(_color);
	}

	/**
	 * Définie l'emplacement de la piece pion
	 * 
	 * @param _location Emplacement sur l'echiquier
	 * @param _color La couleur de la piece
	 */
	public void setPositionPawn(String _location, boolean _color) {
		int y = Helper.getYfromString(_location);
		int x = Helper.getXfromString(_location);
		chessboard[y][x] = new Pion(_color);
	}

	void setnWhiteMoves(int _nWhiteMoves) {
		nWhiteMoves = _nWhiteMoves;
	}

	int getnWhiteMoves() {
		return nWhiteMoves;
	}

	void setnBlackMoves(int _nBlackMoves) {
		nBlackMoves = _nBlackMoves;
	}

	int getnBlackMoves() {
		return nBlackMoves;
	}

	private void setBlacks(List<Piece> _blacks) {
		blacks = _blacks;
	}

	public List<Piece> getBlacks() {
		return blacks;
	}

	private void setWhites(List<Piece> _whites) {
		whites = _whites;
	}

	public List<Piece> getWhites() {
		return whites;
	}

	public void setChessboard(Piece[][] chessboard) {
		this.chessboard = chessboard;
	}

}
