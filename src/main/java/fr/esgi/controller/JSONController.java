package fr.esgi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.esgi.ia.Algorithm;
import fr.esgi.ia.Chessboard;
import fr.esgi.ia.IA;

@Controller
@RequestMapping("/alphabeta")
public class JSONController {

	@RequestMapping(value = "/player1/{p1King}/{p1Queen}/{p1Crazy}/{p1Knight}/{p1Tower}/{p1Pawn}/player2/{p2King}/{p2Queen}/{p2Crazy}/{p2Knight}/{p2Tower}/{p2Pawn}", method = RequestMethod.GET)
	@ResponseBody
	public String getShopInJSON(@PathVariable String p1King, @PathVariable String p1Queen,
			@PathVariable String p1Crazy, @PathVariable String p1Knight, @PathVariable String p1Tower,
			@PathVariable String p1Pawn, @PathVariable String p2King, @PathVariable String p2Queen,
			@PathVariable String p2Crazy, @PathVariable String p2Knight, @PathVariable String p2Tower,
			@PathVariable String p2Pawn) {

		IA ia = new IA();

		Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.isBlack(), p1King, p1Queen, p1Crazy, p1Knight,
				p1Tower, p1Pawn);
		chessboard.insertPieceChessboard(Algorithm.isWhite(), p2King, p2Queen, p2Crazy, p2Knight,
				p2Tower, p2Pawn);

		// Retourne le meilleur coup sous forme de JSON
		return ia.play(Algorithm.isWhite(), 3, chessboard);
	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public String getShopInJSON() {

		IA ia = new IA();

		Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.isBlack(), "d8", "e8", "c8:f8", "b8:g8",
				"a8:h8", "a7:b7:c7:d7:e7:f7:g7:h7");
		chessboard.insertPieceChessboard(Algorithm.isWhite(), "d1", "e1", "c1:f1", "b1:g1",
				"a1:h1", "a2:b2:c2:d2:e2:f2:g2:h2");

		// Retourne le meilleur coup
		return ia.play(Algorithm.isWhite(), 3, chessboard);
	}
}
