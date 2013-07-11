package fr.esgi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

		Chessboard chessboard = new Chessboard();
		chessboard = insertPieceChessboard(chessboard, false, bKing, bQueen, bCrazy, bKnight, bTower, bPawn);
		chessboard = insertPieceChessboard(chessboard, true, wKing, wQueen, wCrazy, wKnight, wTower, wPawn);

		IA ia = new IA();

		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(chessboard.getChessboardForJson());
		} catch (JsonGenerationException e) {
			return e.getMessage();
		} catch (JsonMappingException e) {
			return e.getMessage();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	public Chessboard insertPieceChessboard(Chessboard _chessboard, boolean _color, String _king,
			String _queen, String _crazy, String _knight, String _tower, String _pawn) {
		String[] listLocation;

		// Traitement pour le roi
		if (_king != null) {
			listLocation = _king.split(":");
			for (int i = 0; i < listLocation.length; i++)
				_chessboard.setPositionKing(listLocation[i], _color);
		}

		// Traitement pour la reine
		if (_queen != null) {
			listLocation = _queen.split(":");
			for (int i = 0; i < listLocation.length; i++)
				_chessboard.setPositionQueen(listLocation[i], _color);
		}

		// Traitement pour le fou
		if (_crazy != null) {
			listLocation = _crazy.split(":");
			for (int i = 0; i < listLocation.length; i++)
				_chessboard.setPositionCrazy(listLocation[i], _color);
		}

		// Traitement pour le cavalier
		if (_knight != null) {
			listLocation = _knight.split(":");
			for (int i = 0; i < listLocation.length; i++)
				_chessboard.setPositionKnight(listLocation[i], _color);
		}

		// Traitement pour la tour
		if (_tower != null) {
			listLocation = _tower.split(":");
			for (int i = 0; i < listLocation.length; i++)
				_chessboard.setPositionTower(listLocation[i], _color);
		}

		// Traitement pour le pion
		if (_pawn != null) {
			listLocation = _pawn.split(":");
			for (int i = 0; i < listLocation.length; i++)
				_chessboard.setPositionPawn(listLocation[i], _color);
		}

		return _chessboard;
	}
}
