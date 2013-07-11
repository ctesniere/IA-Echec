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

	@RequestMapping(value = "/p1/{roi}/{reine}/{fou]/{cavalier}/{tour}/{pion}/p2/{roi}/{reine}/{fou]/{cavalier}/{tour}/{pion}",
			method = RequestMethod.GET)
	@ResponseBody
	public Chessboard getShopInJSON(@PathVariable String pions, @PathVariable String tour) {

		IA ia = new IA();
		ia.setMyColor(true);
		ia.setEnemyColor(false);

		ia.play();

		return new Chessboard();
	}

}
