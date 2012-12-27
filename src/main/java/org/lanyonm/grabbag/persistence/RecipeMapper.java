package org.lanyonm.grabbag.persistence;

import java.util.List;

import org.lanyonm.grabbag.domain.Recipe;

public interface RecipeMapper {

	public List<Recipe> getAllRecipes();
	
	public Recipe getRecipe(int id);
}
