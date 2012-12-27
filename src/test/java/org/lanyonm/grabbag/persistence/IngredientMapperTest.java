package org.lanyonm.grabbag.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.persistence.IngredientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={DataConfig.class, IngredientMapper.class})
public class IngredientMapperTest {

	@Autowired
	private IngredientMapper ingredientMapper;

	@Test
	public void testGetAllIngredients() {
		List<Ingredient> ingredients = ingredientMapper.getAllIngredients();
		assertNotNull("ingredients is null", ingredients);
		assertEquals("number of ingredients", 10, ingredients.size());
	}
}
