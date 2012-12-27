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

}
