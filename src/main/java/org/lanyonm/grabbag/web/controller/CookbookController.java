package org.lanyonm.grabbag.web.controller;

//import static com.codahale.metrics.MetricRegistry.name;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.lanyonm.grabbag.web.form.IngredientForm;
import org.lanyonm.grabbag.web.form.RecipeForm;
import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.domain.Recipe;
import org.lanyonm.grabbag.domain.RecipeIngredient;
import org.lanyonm.grabbag.service.CookbookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codahale.metrics.MetricRegistry;
//import com.codahale.metrics.Timer;
import com.codahale.metrics.annotation.Timed;

@Controller
@RequestMapping("/cookbook")
public class CookbookController {

	@Autowired
	private CookbookService cookbookService;
	@Autowired
	private Validator validator;

	final MetricRegistry metrics = new MetricRegistry();
//	private final Timer responses = metrics.timer(name(CookbookController.class, "responses"));
	private static final Logger log = LoggerFactory.getLogger(CookbookController.class);

	@RequestMapping("/")
	public String index(Model model) {
		log.debug("Cookbook index mapping");
		model.addAttribute("recipes", cookbookService.getAllRecipes());
		return "cookbook/index";
	}

	@Timed
	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public String recipe(@PathVariable("id") final int id, Model model) {
		Recipe recipe = cookbookService.getRecipe(id);
		if (recipe == null) {
			model.addAttribute("error", "Something nefarious was attempted.");
			return "redirect:/cookbook/";
		}
		model.addAttribute("recipe", recipe);
		return "cookbook/recipe";
	}

	@RequestMapping(value = "/recipe/{id}/edit", method = RequestMethod.GET)
	public String recipeAddEditShow(@PathVariable("id") final int id, Model model) {
		Recipe recipe = cookbookService.getRecipe(id);
		if (recipe == null && id > 0) {
			model.addAttribute("error", "Something nefarious was attempted.");
			return "redirect:/cookbook/";
		} else if (id == 0) {
			recipe = new Recipe();
		}
		model.addAttribute("recipe", recipe);
		model.addAttribute("allIngredients", cookbookService.getAllIngredients());
		return "cookbook/recipeAddEdit";
	}

	@RequestMapping(value = "/recipe/{id}/edit", method = RequestMethod.POST)
	public String recipeAddEditSave(@PathVariable("id") final int id, Model model, @ModelAttribute("recipe") RecipeForm recipeForm, BindingResult result) {
		Recipe recipe = new Recipe();
		if (id == 0) {
			recipe.setName(recipeForm.getName());
			recipe.setDescription(recipeForm.getDescription());
			cookbookService.saveRecipe(recipe);
		} else {
			recipe = cookbookService.getRecipe(id);
			if (recipe != null) {
				recipe.setName(recipeForm.getName());
				recipe.setDescription(recipeForm.getDescription());
				cookbookService.saveRecipe(recipe);
			} else {
				model.addAttribute("error", "Something nefarious was attempted.");
			}
		}
		return "redirect:/cookbook/";
	}

	@RequestMapping(value = "/recipe/{id}/delete", method = RequestMethod.GET)
	public String deleteRecipe(@PathVariable("id") final int id, Model model) {
		Recipe recipe = cookbookService.getRecipe(id);
		if (cookbookService.deleteRecipe(recipe)) {
			model.addAttribute("message", "You successfully deleted " + recipe.getName() + "!");
		} else {
			model.addAttribute("error", "There was an error deleting the recipe.");
		}
		return "redirect:/cookbook/";
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
		} else if (ingredient == null) {
			ingredient = new Ingredient();
		}
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("isNew", id == 0);
		return "cookbook/ingredientAddEdit";
	}

	@RequestMapping(value = "/ingredient/{id}/edit", method = RequestMethod.POST)
	public String ingredientAddEditSave(@PathVariable("id") final int id, Model model, @ModelAttribute("ingredient") IngredientForm ingredientForm, BindingResult result) {
		model.addAttribute("ingredient", ingredientForm);
		validator.validate(ingredientForm, result);
		if (result.hasErrors()) {
			model.addAttribute("isNew", id == 0);
			return "cookbook/ingredientAddEdit";
		}

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

	/**
	 * This method supplies json for D3
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/graph.json", method = RequestMethod.GET)
	public String graphData(Model model, HttpServletResponse response) {
		List<Recipe> recipes = cookbookService.getAllRecipes();
		List<Ingredient> ingredients = cookbookService.getAllIngredients();
		List<Node> nodes = new ArrayList<Node>();
		for (Recipe recipe : recipes) {
			nodes.add(new Node(recipe.getName(), 1));
		}
		for (Ingredient ingredient : ingredients) {
			nodes.add(new Node(ingredient.getName(), 2));
		}
		model.addAttribute("nodes", nodes);
		List<Link> links = new ArrayList<Link>();
		
		int rCount = 0;
		for (Recipe recipe : recipes) {
			for (Ingredient ri : recipe.getIngredients()) {
				Link link = new Link(rCount, ingredients.indexOf(ri) + recipes.size(), 1);
				log.debug(recipe.getName() + " and " + ri.getName() + " is: " + link);
				links.add(link);
			}
			rCount++;
		}
		model.addAttribute("links", links);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		return "cookbook/graph";
	}

	@RequestMapping(value = "/vis", method = RequestMethod.GET)
	public String visualization(Model model) {
		model.addAttribute("recipes", cookbookService.getAllRecipes());
		model.addAttribute("ingredients", cookbookService.getAllIngredients());
		return "cookbook/vis";
	}
	
	public class Node {
		private String name;
		private int group;
		public Node(String name, int group) {
			this.name = name;
			this.group = group;
		}
		public String getName() {
			return this.name;
		}
		public int getGroup() {
			return this.group;
		}
	}

	public class Link {
		private int source;
		private int target;
		private int value;
		public Link(int source, int target, int value) {
			this.source = source;
			this.target = target;
			this.value = value;
		}
		public int getSource() {
			return this.source;
		}
		public int getTarget() {
			return this.target;
		}
		public int getValue() {
			return this.value;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder("Link = [");
			sb.append("source=").append(source)
				.append(", target=").append(target)
				.append(", value=").append(value).append("]");
			return sb.toString();
		}
	}
}
