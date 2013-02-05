package org.lanyonm.grabbag.web.controller;

import java.util.List;

import org.lanyonm.grabbag.web.form.IngredientForm;
import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.domain.Recipe;
import org.lanyonm.grabbag.service.CookbookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cookbook")
public class CookbookController {

	@Autowired
	private CookbookService cookbookService;

	private static final Logger log = LoggerFactory.getLogger(CookbookController.class);

	@RequestMapping("/")
	public String index(Model model) {
		log.debug("Cookbook index mapping");
		model.addAttribute("recipes", cookbookService.getAllRecipes());
		return "cookbook/index";
	}

	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public String recipe(@PathVariable("id") int id, Model model) {
		log.debug("getting recipe for id " + id);
		model.addAttribute("recipe", cookbookService.getRecipe(id));
		return "cookbook/recipe";
	}

	@RequestMapping("/ingredients")
	public String ingredients(Model model) {
		model.addAttribute("ingredients", cookbookService.getAllIngredients());
		return "cookbook/ingredients";
	}

	@RequestMapping(value = "/ingredient/{id}/edit", method = RequestMethod.GET)
	public String ingredientAddEditShow(@PathVariable("id") final int id, Model model) {
		Ingredient ingredient = cookbookService.getIngredient(id);
		log.debug("when searching for ingredient " + id + ", we found " + ingredient);
		if (ingredient == null && id > 0) {
			model.addAttribute("error", "Something nefarious was attempted.");
			return "redirect:/cookbook/ingredients";
		} else if (ingredient == null && id == 0) {
			ingredient = new Ingredient();
		}
		model.addAttribute("ingredient", ingredient);
		return "cookbook/ingredientAddEdit";
	}

	@RequestMapping(value = "/ingredient/{id}/edit", method = RequestMethod.POST)
	public String ingredientAddEditSave(@PathVariable("id") final int id, Model model, @ModelAttribute("ingredient") IngredientForm ingredientForm, BindingResult result) {
		log.debug("ingredient form posted is: " + ingredientForm);
		// do some validation
		Ingredient ingredient = new Ingredient();
		if (id == 0) {
			ingredient.setName(ingredientForm.getName());
			ingredient.setDescription(ingredientForm.getDescription());
			cookbookService.saveIngredient(ingredient);
		} else {
			ingredient = cookbookService.getIngredient(id);
			if (ingredient != null) {
				ingredient.setName(ingredientForm.getName());
				ingredient.setDescription(ingredientForm.getDescription());
				cookbookService.saveIngredient(ingredient);
			} else {
				model.addAttribute("error", "Something nefarious was attempted.");
			}
		}
		return "redirect:/cookbook/ingredients";
	}

	@RequestMapping(value = "/ingredient/{id}/delete", method = RequestMethod.GET)
	public String ingredientDelete(@PathVariable("id") final int id, Model model) {
		Ingredient ingredient = cookbookService.getIngredient(id);
		if (!cookbookService.getRecipesWithIngredient(ingredient).isEmpty()) {
			List<Recipe> recipes = cookbookService.getRecipesWithIngredient(ingredient);
			StringBuilder builder = new StringBuilder("Couldn't delete the ingredient. ");
			for (Recipe recipe : recipes) {
				builder.append("It is used in " + recipe.getName() + ". ");
			}
			model.addAttribute("error", builder.toString());
		} else {
			if (cookbookService.deleteIngredient(ingredient)) {
				model.addAttribute("message", "You successfully deleted " + ingredient.getName() + "!");
			} else {
				model.addAttribute("error", "There was an error deleteing the ingredient.");
			}
		}
		return "redirect:/cookbook/ingredients";
	}
}
