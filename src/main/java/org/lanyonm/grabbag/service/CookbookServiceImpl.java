package org.lanyonm.grabbag.service;

import java.util.List;

import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.domain.Recipe;
import org.lanyonm.grabbag.persistence.IngredientMapper;
import org.lanyonm.grabbag.persistence.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cookbookService")
public class CookbookServiceImpl implements CookbookService {

	@Autowired
	private RecipeMapper recipeMapper;
	@Autowired
	private IngredientMapper ingredientMapper;

	public List<Recipe> getAllRecipes() {
		return recipeMapper.getAllRecipes();
	}

	public Recipe getRecipe(int id) {
		return recipeMapper.getRecipe(id);
	}

	public int saveRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean deleteRecpie(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Ingredient> getAllIngredients() {
		return ingredientMapper.getAllIngredients();
	}

	public Ingredient getIngredient(int id) {
		return ingredientMapper.getIngredient(id);
	}

	public int saveIngredient(Ingredient ingredient) {
		int ret = 0;
		if (ingredient.getId() > 0) {
			ingredientMapper.updateIngredient(ingredient);
		} else {
			ingredientMapper.insertIngredient(ingredient);
		}
		
		return ret;
	}

	public boolean deleteIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return false;
	}

}
