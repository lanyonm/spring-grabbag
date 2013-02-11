package org.lanyonm.grabbag.dao;

import java.util.List;

import org.lanyonm.grabbag.domain.User;

public interface UsersDao {

	/**
	 * 
	 * @return List<User>
	 */
	public List<User> getUsers();

}
