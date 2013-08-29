import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import fr.esgi.ia.Algorithm;
import fr.esgi.ia.Chessboard;
import fr.esgi.ia.Helper;
import fr.esgi.ia.IA;
import fr.esgi.ia.Move;
import fr.esgi.ia.Piece;

/**
 * Permet de faire les tests sur les pieces de l'échiquier
 * 
 * @author Cédric TESNIERE
 * @since 1
 */
public class TestPiece {

	Chessboard chessboard = new Chessboard();

	@Before
	public void initChessboard() {
		IA ia = new IA();

		chessboard.insertPieceChessboard(Algorithm.isBlack(), "d8", "e8", "c8:f8", "b8:g8",
				"a8:h8", "a7:b7:c7:d7:e7:f7:g7:h7");
		chessboard.insertPieceChessboard(Algorithm.isWhite(), "d1", "e1", "c1:f1", "b1:g1",
				"a1:h1", "a2:b2:c2:d2:e2:f2:g2:h2");
	}

	@Test
	public void testKing() {
		String location = "d1";
		int x = Helper.getXfromString(location);
		int y = Helper.getYfromString(location);
		Piece piece = chessboard.getPiece(x, y);

		// Vérifie si la méthode trouve des positions
		ArrayList<Move> moves = piece.generateMovesForThisPiece(chessboard);
		Assert.notNull(moves);

		// Vérifie si le déplacement suivant est possible
		// Assert.assertEquals(moves.size(), 0);
	}

	@Test
	public void testQueen() {
		String location = "e1";
		Piece piece = chessboard.getPiece(Helper.getXfromString(location),
				Helper.getYfromString(location));

		// Vérifie si la méthode trouve des positions
		ArrayList<Move> moves = piece.generateMovesForThisPiece(chessboard);
		Assert.notNull(moves);

		// Vérifie si le déplacement suivant est possible
		// Assert.assertEquals(moves.size(), 0);
	}

	@Test
	public void testCrazy() {
		String location = "c1";
		Piece piece = chessboard.getPiece(Helper.getXfromString(location),
				Helper.getYfromString(location));

		// Vérifie si la méthode trouve des positions
		ArrayList<Move> moves = piece.generateMovesForThisPiece(chessboard);
		Assert.notNull(moves);

		// Vérifie si le déplacement suivant est possible
		// Assert.assertEquals(moves.size(), 0);
	}

	@Test
	public void testKnight() {
		String location = "b1";
		Piece piece = chessboard.getPiece(Helper.getXfromString(location),
				Helper.getYfromString(location));

		// Vérifie si la méthode trouve des positions
		ArrayList<Move> moves = piece.generateMovesForThisPiece(chessboard);
		Assert.notNull(moves);

		// Vérifie si le déplacement suivant est possible
		// Assert.assertEquals(moves.size(), 2);
	}

	@Test
	public void testTower() {
		String location = "a1";
		Piece piece = chessboard.getPiece(Helper.getXfromString(location),
				Helper.getYfromString(location));

		// Vérifie si la méthode trouve des positions
		ArrayList<Move> moves = piece.generateMovesForThisPiece(chessboard);
		Assert.notNull(moves);

		// Vérifie si le déplacement suivant est possible
		// Assert.assertEquals(moves.size(), 0);
	}

	@Test
	public void testPawn() {
		String location = "a2";
		Piece piece = chessboard.getPiece(Helper.getXfromString(location),
				Helper.getYfromString(location));

		// Vérifie si la méthode trouve des positions
		ArrayList<Move> moves = piece.generateMovesForThisPiece(chessboard);
		Assert.notNull(moves);

		// Vérifie si le déplacement suivant est possible
		// Assert.assertEquals(moves.size(), 2);
	}

}
