package org.lanyonm.grabbag.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.persistence.IngredientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Tests for {@link IngredientMapper}
 * 
 * @author lanyonm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={DataConfig.class})
public class IngredientMapperTest {

	@Autowired
	private IngredientMapper ingredientMapper;

	/**
	 * @see IngredientMapper#getAllIngredients()
	 */
	@Test
	public void testGetAllIngredients() {
		List<Ingredient> ingredients = ingredientMapper.getAllIngredients();
		assertNotNull("ingredients is null", ingredients);
		assertEquals("number of ingredients", 10, ingredients.size());
	}

	/**
	 * Test getting milk.
	 * 
	 * @see IngredientMapper#getIngredient(int)
	 */
	@Test
	public void testGetIngredient() {
		Ingredient ingredient = ingredientMapper.getIngredient(1);
		assertNotNull("ingredient is null", ingredient);
		assertEquals("ingredient name", "milk", ingredient.getName());
		assertEquals("ingredient description", "it comes from cows", ingredient.getDescription());
	}

	/**
	 * @see IngredientMapper#updateIngredient(Ingredient)
	 */
	@Test
	public void testUpdateIngredient() {
		Ingredient ingredient = ingredientMapper.getIngredient(2);
		ingredient.setName("test butter");
		ingredientMapper.updateIngredient(ingredient);
		ingredient = ingredientMapper.getIngredient(2);
		assertEquals("test butter", ingredient.getName());
	}

	/**
	 * @see IngredientMapper#insertIngredient(Ingredient)
	 */
	@Test
	@Ignore("need to fix key generation")
	public void testInsertIngredientSuccess() {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("testIngredient");
		ingredient.setDescription("this is just for testing");
		ingredientMapper.insertIngredient(ingredient);
		List<Ingredient> ingredients = ingredientMapper.getAllIngredients();
		assertEquals(11, ingredients.size());
		assertEquals("testIngredient", ingredientMapper.getIngredient(11).getName());
	}

	/**
	 * @see IngredientMapper#insertIngredient(Ingredient)
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testInsertIngredientDuplicateId() {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("margarine");
		ingredient.setDescription("isn't it true?!?");
		ingredientMapper.insertIngredient(ingredient);
	}

}
