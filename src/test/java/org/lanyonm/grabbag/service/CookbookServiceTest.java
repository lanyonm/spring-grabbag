package org.lanyonm.grabbag.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.domain.Recipe;
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
		assertEquals("number of recipes", 1, recipes.size());
	}
}
