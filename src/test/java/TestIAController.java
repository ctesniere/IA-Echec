import org.junit.Assert;
import org.junit.Test;

import fr.esgi.ia.Algorithm;
import fr.esgi.ia.Chessboard;
import fr.esgi.ia.IA;
import fr.esgi.ia.Piece;

public class TestIAController {

	@Test
	public void testIA() {

		Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.isBlack(), "d8", "e8", "c8:f8", "b8:g8",
				"a8:h8", "a7:b7:c5:d7:e7:f7:g7:h7");
		chessboard.insertPieceChessboard(Algorithm.isWhite(), "d4", "e1", "c1:f1", "c3:g1",
				"a1:h1", "a2:b4:c2:d2:e2:f2:g2:h4");

		IA ia = new IA();
		System.out.println(ia.play(Algorithm.isWhite(), 2, chessboard));
	}

}
