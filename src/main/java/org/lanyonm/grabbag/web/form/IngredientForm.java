package org.lanyonm.grabbag.web.form;

public class IngredientForm {

	private String name;
	private String description;

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

	public String toString() {
		return new StringBuilder("web.form.IngredientForm: name=\"").append(this.name)
				.append("\", description=\"").append(this.description)
				.append("\"").toString();
	}
}
