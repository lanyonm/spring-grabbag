package org.lanyonm.grabbag.domain;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String description;
	private int type;
	private List<Ingredient> ingredients;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return (obj instanceof Recipe &&
			((Recipe) obj).getId() == this.getId() &&
			(this.getName() == null ? ((Recipe) obj).getName() == null : this.getName().equals(((Recipe) obj).getName())) &&
			(this.getDescription() == null ? ((Recipe) obj).getDescription() == null : this.getDescription().equals(((Recipe) obj).getDescription())));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recipe [id=").append(getId())
			.append(", name=").append(getName())
			.append(", type=").append(getType())
			.append(", description=").append(getDescription())
			.append("]");
		return builder.toString();
	}
}
