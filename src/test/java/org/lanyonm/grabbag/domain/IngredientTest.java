package org.lanyonm.grabbag.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class IngredientTest {

	private Ingredient one;
	private Ingredient two;

	@Before
	public void setup() {
		one = new Ingredient();
		one.setName("Test");
		two = new Ingredient();
		two.setName("Test");
	}

	@Test
	public void testEquals() {
		assertThat(one, equalTo(one));
		assertThat("ingredients with the same name and description should be equal", one, equalTo(two));
		one.setDescription("desc");
		two.setDescription("desc");
		assertThat("ingredients with the same name and description should be equal", one, equalTo(two));
	}

	@Test
	public void testNotEquals() {
		assertThat("recipe compare to null", one, not(equalTo(null)));
		assertThat("recipe compare to non-recipe", one, not(equalTo(new Object())));
		Ingredient three = new Ingredient();
		assertThat("recipe compare to empty recipe", one, not(equalTo(three)));
		// id
		three.setId(10);
		assertThat("recipe compare to recipe with different id", one, not(equalTo(three)));
		three.setId(0);
		// name
		assertThat("recipe compare to recipe with different name", three, not(equalTo(one)));
		three.setName("three");
		assertThat("recipe compare to recipe with different name", one, not(equalTo(three)));
		three.setName("Test");
		// description
		one.setDescription("one");
		assertThat("recipe compare to recipe with different description", three, not(equalTo(one)));
		three.setDescription("desc");
		assertThat("recipe compare to recipe with different description", one, not(equalTo(three)));
	}
}
