package org.lanyonm.grabbag.domain;

import java.io.Serializable;

/**
 * Generic container for a cooking ingredient
 * 
 * @author lanyonm
 */
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String description;

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return (obj instanceof Ingredient &&
			((Ingredient) obj).getId() == this.getId() &&
			(this.getName() == null ? ((Ingredient) obj).getName() == null : this.getName().equals(((Ingredient) obj).getName())) &&
			(this.getDescription() == null ? ((Ingredient) obj).getDescription() == null : this.getDescription().equals(((Ingredient) obj).getDescription())));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ingredient [id=").append(getId())
			.append(", name=").append(getName())
			.append(", description=").append(getDescription())
			.append("]");
		return builder.toString();
	}
}
