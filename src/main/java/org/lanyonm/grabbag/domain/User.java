package org.lanyonm.grabbag.domain;

import java.io.Serializable;

/**
 * 
 * @author mlanyon
 */
public class User implements Serializable {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private static final long serialVersionUID = 1L;

	public User() { }

	public User(long id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
