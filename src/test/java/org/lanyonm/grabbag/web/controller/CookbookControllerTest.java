package org.lanyonm.grabbag.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.config.ViewResolver;
import org.lanyonm.grabbag.config.WebConfig;
import org.lanyonm.grabbag.service.CookbookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/cookbook/"))
			.andExpect(status().isOk())
				.andExpect(view().name("cookbook/index"))
				.andExpect(model().attributeExists("recipes"));
//		this.mockMvc.perform(get("/cookbook/"))
//				.andDo(print());
	}

	@Test
	public void testIngredientEditPost() throws Exception {
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 1).param("name", "milk").param("description", "it comes from cows!"))
				.andExpect(redirectedUrl("/cookbook/ingredients"));
		/*
		 * expect the error return in the model on fail
		 */
		this.mockMvc.perform(post("/cookbook/ingredient/{id}/edit", 1).param("name", "").param("description", "it comes from cows!")
				.locale(Locale.CANADA_FRENCH))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeErrorCount("ingredient", 1))
				.andExpect(model().attributeHasFieldErrors("ingredient", "name"));
	}
}
