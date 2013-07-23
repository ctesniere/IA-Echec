package fr.esgi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.esgi.ia.Chessboard;
import fr.esgi.ia.IA;

@Controller
@RequestMapping("/alphabeta")
public class JSONController {

	@RequestMapping(value = "/black/{bKing}/{bQueen}/{bCrazy}/{bKnight}/{bTower}/{bPawn}/white/{wKing}/{wQueen}/{wCrazy}/{wKnight}/{wTower}/{wPawn}",
			method = RequestMethod.GET)
	@ResponseBody
	public String getShopInJSON(@PathVariable String bKing, @PathVariable String bQueen,
			@PathVariable String bCrazy, @PathVariable String bKnight, @PathVariable String bTower,
			@PathVariable String bPawn, @PathVariable String wKing, @PathVariable String wQueen,
			@PathVariable String wCrazy, @PathVariable String wKnight, @PathVariable String wTower,
			@PathVariable String wPawn) {

		IA ia = new IA();
		
		Chessboard chessboard = new Chessboard();
		chessboard.insertPieceChessboard(ia.isBlack(), bKing, bQueen, bCrazy, bKnight, bTower, bPawn);
		chessboard.insertPieceChessboard(ia.isWhite(), wKing, wQueen, wCrazy, wKnight, wTower, wPawn);
		
		// Retourne le meilleur coup
		return ia.play(ia.isMyColor(), 3, chessboard);

		// try {
		// ObjectMapper mapper = new ObjectMapper();
		// return mapper.writeValueAsString(chessboard.getChessboardForJson());
		// } catch (JsonGenerationException e) {
		// return e.getMessage();
		// } catch (JsonMappingException e) {
		// return e.getMessage();
		// } catch (IOException e) {
		// return e.getMessage();
		// }
	}
}
