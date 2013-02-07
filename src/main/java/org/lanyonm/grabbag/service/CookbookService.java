package org.lanyonm.grabbag.service;

import java.util.List;

import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.domain.Recipe;

public interface CookbookService {

	public List<Recipe> getAllRecipes();
	public Recipe getRecipe(int id);
	/**
	 * 
	 * @param recipe
	 * @return Was the {@link Recipe} successfully saved?
	 */
	public boolean saveRecipe(Recipe recipe);
	public boolean deleteRecipe(Recipe recipe);

	public List<Ingredient> getAllIngredients();
	public Ingredient getIngredient(int id);
	/**
	 * 
	 * @param ingredient
	 * @return Was the {@link Ingredient} successfully saved?
	 */
	public boolean saveIngredient(Ingredient ingredient);
	public boolean deleteIngredient(Ingredient ingredient);
	
	public List<Recipe> getRecipesWithIngredient(Ingredient ingredient);
}
