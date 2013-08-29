import org.junit.Assert;
import org.junit.Test;

import fr.esgi.ia.Algorithm;
import fr.esgi.ia.Chessboard;
import fr.esgi.ia.IA;

public class TestIAController {

	@Test
	public void testIA() {

		Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.isBlack(), "d8", "e8", "c8:f8", "b8:g8",
				"a8:h8", "a7:b7:c7:d7:e7:f7:g7:h7");
		chessboard.insertPieceChessboard(Algorithm.isWhite(), "d1", "e1", "c1:f1", "b1:g1",
				"a1:h1", "a2:b2:c2:d2:e2:f2:g2:h2");

		IA ia = new IA();
		System.out.println(ia.play(Algorithm.isWhite(), 2, chessboard));
		Assert.assertNotEquals(ia.play(Algorithm.isWhite(), 2, chessboard),
				"ERROR: Pas de mouvement possible.");
	}

}
