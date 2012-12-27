package org.lanyonm.grabbag.domain;

/**
 * An {@link Ingredient} plus the volume
 * 
 * @author lanyonm
 */
public class RecipeIngredient extends Ingredient {

	private static final long serialVersionUID = 1L;

	private String volume;

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

}
