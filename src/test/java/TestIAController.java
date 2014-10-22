import org.junit.Test;

import fr.esgi.ia.Algorithm;
import fr.esgi.ia.Chessboard;
import fr.esgi.ia.IA;

public class TestIAController {

	@Test
	public void testIA() {

		final Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.BLACK, "d8", "e8", "c8:f8", "b8:g8",
				"a8:h8", "a7:b7:c5:d7:e7:f7:g7:h7");
		chessboard.insertPieceChessboard(Algorithm.WHITE, "d1", "e1", "c1:f1", "b1:g1",
				"a1:h1", "a2:b4:c2:d2:e2:f2:g2:h2");

		final IA ia = new IA();
		System.out.println(ia.play(Algorithm.WHITE, 2, chessboard));
	}

}
