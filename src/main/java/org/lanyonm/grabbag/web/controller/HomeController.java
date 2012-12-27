package org.lanyonm.grabbag.web.controller;

import org.lanyonm.grabbag.dao.UsersDao;
import org.lanyonm.grabbag.persistence.IngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private IngredientMapper ingredientMapper;
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	/**
	 * A basic homepage mapping.  This controller action is passing a string
	 * to the view.
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void index(Model model) {
		log.debug("In the HomeController::index");
		model.addAttribute("sample", "Hello World!");
	}

	@RequestMapping("/defined-path")
	public String definedPath(Model model) {
		log.debug("hit the defined path");
		String viewPath = "testView";
		model.addAttribute("sample", "Defined Path!!!");
		return viewPath;
	}
	
	@RequestMapping(value = "/jdbc", method = RequestMethod.GET)
	public void jdbc(Model model) {
		model.addAttribute("users", usersDao.getUsers());
	}

	@RequestMapping(value = "/mybatis", method = RequestMethod.GET)
	public void mybatis(Model model) {
		model.addAttribute("ingredients", ingredientMapper.getAllIngredients());
	}
}
