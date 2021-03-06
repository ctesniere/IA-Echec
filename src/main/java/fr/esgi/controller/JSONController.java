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

	@RequestMapping(value = "/{roundPlayer}/black/{bKing}/{bQueen}/{bCrazy}/{bKnight}/{bTower}/{bPawn}/white/{wKing}/{wQueen}/{wCrazy}/{wKnight}/{wTower}/{wPawn}", method = RequestMethod.GET)
	@ResponseBody
	public String getShopInJSON(@PathVariable boolean roundPlayer, @PathVariable String bKing,
			@PathVariable String bQueen, @PathVariable String bCrazy, @PathVariable String bKnight,
			@PathVariable String bTower, @PathVariable String bPawn, @PathVariable String wKing,
			@PathVariable String wQueen, @PathVariable String wCrazy, @PathVariable String wKnight,
			@PathVariable String wTower, @PathVariable String wPawn) {

		final IA ia = new IA();

		final Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.BLACK, bKing, bQueen, bCrazy, bKnight,
				bTower, bPawn);
		chessboard.insertPieceChessboard(Algorithm.WHITE, wKing, wQueen, wCrazy, wKnight,
				wTower, wPawn);

		// Retourne le meilleur coup sous forme de JSON
		return ia.play(roundPlayer, 3, chessboard);
	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public String getShopInJSON() {

		final IA ia = new IA();

		final Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(Algorithm.BLACK, "d8", "e8", "c8:f8", "b8:g8",
				"a8:h8", "a7:b7:c7:d7:e7:f7:g7:h7");
		chessboard.insertPieceChessboard(Algorithm.WHITE, "d1", "e1", "c1:f1", "b1:g1",
				"a1:h1", "a2:b2:c2:d2:e2:f2:g2:h2");

		// Retourne le meilleur coup
		return ia.play(Algorithm.WHITE, 3, chessboard);
	}
}
