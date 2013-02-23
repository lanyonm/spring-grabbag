package org.lanyonm.grabbag.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.config.ViewResolver;
import org.lanyonm.grabbag.config.WebConfig;
import org.lanyonm.grabbag.domain.Ingredient;
import org.lanyonm.grabbag.service.CookbookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader=AnnotationConfigWebContextLoader.class, classes={DataConfig.class, WebConfig.class, ViewResolver.class,
	CookbookController.class, CookbookServiceImpl.class})
public class CookbookControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/cookbook/"))
				.andExpect(status().isOk())
				.andExpect(view().name("cookbook/index"))
				.andExpect(model().attributeExists("recipes"));
	}

	@Test
	public void testGetRecipe() throws Exception {
		this.mockMvc.perform(get("/cookbook/recipe/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("cookbook/recipe"))
				.andExpect(model().attributeExists("recipe"));
		this.mockMvc.perform(get("/cookbook/recipe/{id}", Integer.MAX_VALUE))
				.andExpect(redirectedUrl("/cookbook/?error=Something+nefarious+was+attempted."));
	}

	@Test
	public void testRecipeAddEdit() throws Exception {
		this.mockMvc.perform(get("/cookbook/recipe/{id}/edit", Integer.MAX_VALUE))
				.andExpect(redirectedUrl("/cookbook/?error=Something+nefarious+was+attempted."));
		this.mockMvc.perform(get("/cookbook/recipe/{id}/edit", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("cookbook/recipeAddEdit"))
				.andExpect(model().size(2))
				.andExpect(model().attributeExists("recipe", "allIngredients"));
		this.mockMvc.perform(get("/cookbook/recipe/{id}/edit", 0))
				.andExpect(status().isOk())
				.andExpect(view().name("cookbook/recipeAddEdit"))
				.andExpect(model().size(2))
				.andExpect(model().attributeExists("recipe", "allIngredients"));
	}

	@Test
	public void testIngredients() throws Exception {
		this.mockMvc.perform(get("/cookbook/ingredients"))
				.andExpect(status().isOk())
				.andExpect(view().name("cookbook/ingredients"))
				.andExpect(model().attributeExists("ingredients"));
	}

	@Test
	public void testIngredientEdit() throws Exception {
		this.mockMvc.perform(get("/cookbook/ingredient/{id}/edit", 1).locale(Locale.FRENCH).accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("cookbook/ingredientAddEdit"))
				.andExpect(model().size(2))
				.andExpect(model().attributeExists("ingredient", "isNew"));
				// We cannot test the jsps and therefore don't have a way to test i18n
				// .andExpect(content().string(containsString("Nom")));
	}

	@Test
	public void testIngredientEditPost() throws Exception {
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 1).param("name", "milk").param("description", "it comes from cows!"))
				.andExpect(redirectedUrl("/cookbook/ingredients"));
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 1).param("name", "").param("description", "it comes from cows!"))
				.andExpect(status().isOk())
				.andExpect(model().attributeErrorCount("ingredient", 1))
				.andExpect(model().attributeHasFieldErrors("ingredient", "name"));
	}

	@Test
	public void testIngredientAddDelete() throws Exception {
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 0).param("name", "test ingredient").param("description", "this is a test ingredient"))
				.andExpect(redirectedUrl("/cookbook/ingredients"));
		MvcResult result = this.mockMvc.perform(get("/cookbook/ingredients"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("ingredients"))
				.andReturn();
		@SuppressWarnings("unchecked")
		List<Ingredient> ingredients = (List<Ingredient>) result.getModelAndView().getModel().get("ingredients");
		long ingredientId = ingredients.get(ingredients.size() - 1).getId();
		assertTrue("ingredientId should be be a positive int", ingredientId > 0);
		this.mockMvc.perform(get("/cookbook/ingredient/{id}/delete", ingredientId))
				.andExpect(redirectedUrl("/cookbook/ingredients?message=You+successfully+deleted+test+ingredient%21"));
	}

}
