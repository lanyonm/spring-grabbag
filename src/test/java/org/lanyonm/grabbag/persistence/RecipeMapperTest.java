package org.lanyonm.grabbag.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.util.Assert.isInstanceOf;

import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.domain.Recipe;
import org.lanyonm.grabbag.domain.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Tests {@link RecipeMapper}
 * 
 * @author lanyonm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={DataConfig.class})
public class RecipeMapperTest {

	@Autowired
	private RecipeMapper recipeMapper;

	/**
	 * @see RecipeMapper#getAllRecipes()
	 */
	@Test
	public void testGetAllRecipes() {
		List<Recipe> recipes = recipeMapper.getAllRecipes();
		assertNotNull(recipes);
		assertEquals("number of recipes", 2, recipes.size());
	}

	/**
	 * Lookup Pistachio Dessert
	 * 
	 * @see RecipeMapper#getRecipe(int)
	 */
	@Test
	public void testGetRecipe() {
		Recipe recipe = recipeMapper.getRecipe(1);
		assertNotNull(recipe);
		assertEquals("recipe name", "Pistachio Dessert", recipe.getName());
		assertEquals("recipe description", null, recipe.getDescription());
		assertNotNull("recipe ingredients", recipe.getIngredients());
		assertEquals("number of ingredients", 9, recipe.getIngredients().size());
		for (Object ingredient : recipe.getIngredients()) {
			isInstanceOf(RecipeIngredient.class, ingredient, "ingredients are RecipeIngredients");
			assertNotNull(((RecipeIngredient) ingredient).getName());
			assertNotNull(((RecipeIngredient) ingredient).getName() + " volume", ((RecipeIngredient) ingredient).getVolume());
		}
	}

	@Test
	@Ignore("implement this")
	public void testUpdateRecipe() {
		fail("test something");
	}

	@Test
	public void testInsertRecipeSuccess() {
		Recipe recipe = new Recipe();
		recipe.setName("instant cream cheese pudding");
		assertEquals("only 1 row should have been affected", 1, recipeMapper.insertRecipe(recipe));
		assertTrue("new recipe id should be greater than 2", recipe.getId() > 2);
	}

	@Test
	public void testInsertRecipeIngredientSuccess() {
		assertEquals("only 1 ingredient in recipeId 2", 1, recipeMapper.getRecipe(2).getIngredients().size());
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("recipeId", 2);
		params.put("ingredientId", 2);
		params.put("volume", "2 sticks");
		assertEquals("only 1 row should have been affected", 1, recipeMapper.insertRecipeIngredient(params));
		assertEquals("should be 2 ingredients in recipeId 2", 2, recipeMapper.getRecipe(2).getIngredients().size());
	}

	@Test
	public void testDeleteRecipeSuccess() {
		assertEquals("there should be 3 recipes", 3, recipeMapper.getAllRecipes().size());
		Recipe recipe = recipeMapper.getRecipe((int) recipeMapper.getAllRecipes().get(2).getId());
		assertNotNull("recipe should not be null", recipe);
		assertEquals("only 1 row should be affected", 1, recipeMapper.deleteRecipe(recipe));
		assertEquals("there should be 2 recipes", 2, recipeMapper.getAllRecipes().size());
	}
}
