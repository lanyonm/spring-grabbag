package org.lanyonm.grabbag.service;

import java.util.List;

import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.domain.Recipe;

public interface CookbookService {

	public List<Recipe> getAllRecipes();
	public Recipe getRecipe(int id);
	public int saveRecipe(Recipe recipe);
	public boolean deleteRecipe(Recipe recipe);

	public List<Ingredient> getAllIngredients();
	public Ingredient getIngredient(int id);
	public int saveIngredient(Ingredient ingredient);
	public boolean deleteIngredient(Ingredient ingredient);
	
	public List<Recipe> getRecipesWithIngredient(Ingredient ingredient);
}
