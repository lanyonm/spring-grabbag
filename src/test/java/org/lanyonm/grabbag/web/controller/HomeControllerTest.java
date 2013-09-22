package org.lanyonm.grabbag.web.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.config.ViewResolver;
import org.lanyonm.grabbag.config.WebConfig;
import org.lanyonm.grabbag.dao.jdbc.UsersDaoImpl;
import org.lanyonm.grabbag.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader=AnnotationConfigWebContextLoader.class, classes={DataConfig.class, WebConfig.class, ViewResolver.class, HomeController.class, UsersDaoImpl.class})
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testDefinedPath() throws Exception {
		this.mockMvc.perform(get("/defined-path")).andExpect(status().isOk())
			.andExpect(model().attribute("sample", "Defined Path!!!"));
	}

	@Test
	public void testJdbc() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/jdbc")).andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("users"))
			.andReturn();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) result.getModelAndView().getModel().get("users");
		assertEquals("there should be two users", 2, users.size());
		assertEquals("first user's name should be 'Mike L. <lanyonm@gmail.com>'", "Mike L. <lanyonm@gmail.com>", users.get(0).toString());
	}

	@Test
	public void testMyBatis() throws Exception {
		this.mockMvc.perform(get("/mybatis")).andExpect(status().isOk())
			.andExpect(model().attributeExists("ingredients"));
	}
}
