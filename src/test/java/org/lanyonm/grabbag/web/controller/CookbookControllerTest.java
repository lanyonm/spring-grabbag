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
import static org.junit.Assert.assertEquals;
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
import org.lanyonm.grabbag.domain.Recipe;
import org.lanyonm.grabbag.service.CookbookServiceImpl;
import org.lanyonm.grabbag.web.controller.CookbookController.Link;
import org.lanyonm.grabbag.web.controller.CookbookController.Node;
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
	public void testRecipeEditPost() throws Exception {
		this.mockMvc.perform(post("/cookbook/recipe/{id}/edit", Integer.MAX_VALUE))
				.andExpect(redirectedUrl("/cookbook/?error=Something+nefarious+was+attempted."));
		Recipe testRecipe = new Recipe();
		testRecipe.setId(1);
		testRecipe.setName("Pistachio Dessert");
		this.mockMvc.perform(get("/cookbook/recipe/{id}/edit", 1))
				.andExpect(status().isOk())
				.andExpect(model().attribute("recipe", testRecipe));
		this.mockMvc.perform(post("/cookbook/recipe/{id}/edit", 1).param("name", "Pistachio Dessert").param("description", "test description"))
				.andExpect(redirectedUrl("/cookbook/"));

	}

	@Test
	public void testRecipeAddDelete() throws Exception {
		this.mockMvc.perform(post("/cookbook/recipe/{id}/edit", 0).param("name", "Test Recipe").param("description", "A recipe used during testing"))
				.andExpect(redirectedUrl("/cookbook/"));
		MvcResult result = this.mockMvc.perform(get("/cookbook/"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipes"))
				.andReturn();
		@SuppressWarnings("unchecked")
		List<Recipe> recipes = (List<Recipe>) result.getModelAndView().getModel().get("recipes");
		assertEquals("there should be three recipes", recipes.size(), 3);
		long recipeId = recipes.get(recipes.size() -1).getId();
		this.mockMvc.perform(get("/cookbook/recipe/{id}/delete", recipeId))
				.andExpect(redirectedUrl("/cookbook/?message=You+successfully+deleted+Test+Recipe%21"));
		result = this.mockMvc.perform(get("/cookbook/?message=You+successfully+deleted+Test+Recipe%21"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipes"))
				.andReturn();
		@SuppressWarnings("unchecked")
		List<Recipe> recipes2 = (List<Recipe>) result.getModelAndView().getModel().get("recipes");
		assertEquals("there should be two recipes", recipes2.size(), 2);
	}

	@Test
	public void testRecipeDeleteFailure() throws Exception {
		this.mockMvc.perform(get("/cookbook/recipe/{id}/delete", Integer.MAX_VALUE))
				.andExpect(redirectedUrl("/cookbook/?error=There+was+an+error+deleting+the+recipe."));
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
		this.mockMvc.perform(get("/cookbook/ingredient/{id}/edit", Integer.MAX_VALUE))
				.andExpect(redirectedUrl("/cookbook/ingredients?error=Something+nefarious+was+attempted."));
		this.mockMvc.perform(get("/cookbook/ingredient/{id}/edit", 0))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("ingredient", "isNew"));
	}

	@Test
	public void testIngredientEditPost() throws Exception {
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 1).param("name", "milk").param("description", "it comes from cows!"))
				.andExpect(redirectedUrl("/cookbook/ingredients"));
		// expect some errors
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 1).param("name", "").param("description", "it comes from cows!"))
				.andExpect(status().isOk())
				.andExpect(model().size(2))
				.andExpect(model().attributeErrorCount("ingredient", 1))
				.andExpect(model().attributeHasFieldErrors("ingredient", "name"))
				.andExpect(model().attribute("isNew", false))
				.andExpect(view().name("cookbook/ingredientAddEdit"));
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", Integer.MAX_VALUE).param("name", "test name").param("description", "test description"))
				.andExpect(redirectedUrl("/cookbook/ingredients?error=Something+nefarious+was+attempted."));
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
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 0).param("name", "").param("description", "this is a test ingredient"))
				.andExpect(status().isOk())
				.andExpect(model().size(2))
				.andExpect(model().attributeErrorCount("ingredient", 1))
				.andExpect(model().attributeHasFieldErrors("ingredient", "name"))
				.andExpect(model().attribute("isNew", true))
				.andExpect(view().name("cookbook/ingredientAddEdit"));
	}

	@Test
	public void testVisualization() throws Exception {
		this.mockMvc.perform(get("/cookbook/vis"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipes", "ingredients"))
				.andExpect(view().name("cookbook/vis"))
				.andReturn();
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGraphJSON() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/cookbook/graph.json"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("nodes", "links"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		List<Node> nodes = (List<Node>) result.getModelAndView().getModel().get("nodes");
		assertEquals("first node name should be first recipe", "Pistachio Dessert", nodes.get(0).getName());
		assertEquals("first node's group should be one", 1, nodes.get(0).getGroup());
		List<Link> links = (List<Link>) result.getModelAndView().getModel().get("links");
		assertEquals("first link's source should be 0", 0, links.get(0).getSource());
		assertEquals("first link's target should be 7", 7, links.get(0).getTarget());
		assertEquals("first link's value should be 1", 1, links.get(0).getValue());
	}
}
