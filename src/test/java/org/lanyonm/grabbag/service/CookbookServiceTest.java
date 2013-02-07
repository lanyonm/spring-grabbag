package org.lanyonm.grabbag.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.domain.Recipe;
import org.lanyonm.grabbag.domain.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={DataConfig.class, CookbookServiceImpl.class})
public class CookbookServiceTest {

	@Autowired
	private CookbookService cookbookService;

	@Test
	public void testGetAllRecipes() {
		List<Recipe> recipes = cookbookService.getAllRecipes();
		assertNotNull("recipes null", recipes);
		assertEquals("number of recipes", 2, recipes.size());
	}

	@Test
	public void testDeleteIngredient() {
		Ingredient ingredient = cookbookService.getIngredient(2);
		assertEquals("should have butter", "butter", ingredient.getName());
		assertTrue(cookbookService.deleteIngredient(ingredient));
		assertNull("should have null", cookbookService.getIngredient(2));
	}

	@Test
	public void testDeleteIngredientWithDependency() {
		Ingredient ingredient = cookbookService.getIngredient(8);
		assertEquals("should have Cool Whip", "Cool Whip", ingredient.getName());
		assertFalse(cookbookService.deleteIngredient(ingredient));
	}

	@Test
	public void testInsertRecipeSuccess() {
		Recipe recipe = new Recipe();
		recipe.setName("instant cream cheese pudding");
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		RecipeIngredient recipeIngredient = new RecipeIngredient();
		recipeIngredient.setId(7);
		recipeIngredient.setVolume("1/4 cup");
		ingredients.add(recipeIngredient);
		recipeIngredient = new RecipeIngredient();
		recipeIngredient.setId(9);
		recipeIngredient.setVolume("2 pkg");
		ingredients.add(recipeIngredient);
		recipe.setIngredients(ingredients);
		ingredients.add(recipeIngredient);
		assertTrue("only 1 row should have been affected", cookbookService.saveRecipe(recipe));
		recipe = cookbookService.getRecipe(3);
		assertNotNull(recipe);
		assertEquals("recipe name should be 'instant cream cheese pudding'", "instant cream cheese pudding", cookbookService.getRecipe(3).getName());
	}

	@Test
	public void testDeleteRecipeSuccess() {
		Recipe recipe = cookbookService.getRecipe(3);
		assertNotNull(recipe);
		assertEquals("should be 3 recipes", 3, cookbookService.getAllRecipes().size());
		assertTrue("should have deleted recipe", cookbookService.deleteRecipe(recipe));
		assertEquals("should be 2 recipes", 2, cookbookService.getAllRecipes().size());
	}
}
