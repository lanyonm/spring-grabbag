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
		int ret = 0;
		if (recipe.getId() > 0) {
			ret = recipeMapper.updateRecipe(recipe);
		} else {
			ret = recipeMapper.insertRecipe(recipe);
		}
		return ret;
	}

	public boolean deleteRecipe(Recipe recipe) {
		return recipeMapper.deleteRecipe(recipe) > 0;
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
			ret = ingredientMapper.updateIngredient(ingredient);
		} else {
			ret = ingredientMapper.insertIngredient(ingredient);
		}
		return ret;
	}

	public boolean deleteIngredient(Ingredient ingredient) {
		if (!getRecipesWithIngredient(ingredient).isEmpty()) {
			return false;
		} else {
			return ingredientMapper.deleteIngredient(ingredient) > 0;
		}
	}

	public List<Recipe> getRecipesWithIngredient(Ingredient ingredient) {
		return recipeMapper.getRecipeWithIngredient(ingredient);
	}

}
