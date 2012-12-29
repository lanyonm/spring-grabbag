package org.lanyonm.grabbag.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.util.Assert.isInstanceOf;

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
		assertEquals("number of recipes", 1, recipes.size());
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
	@Ignore("implement this")
	public void testInsertRecipeSuccess() {
		fail("test something");
	}

}
