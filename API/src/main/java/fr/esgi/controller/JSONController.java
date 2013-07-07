package fr.esgi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/get")
public class JSONController {

	@RequestMapping(value = "/{pions}", method = RequestMethod.GET)
	public @ResponseBody
	Shop getShopInJSON(@PathVariable String pions) {

		Shop shop = new Shop();
		shop.setName(pions);
		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });

		return shop;
	}

}