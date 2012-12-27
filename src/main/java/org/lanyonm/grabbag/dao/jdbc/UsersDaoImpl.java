package org.lanyonm.grabbag.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.lanyonm.grabbag.dao.UsersDao;
import org.lanyonm.grabbag.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * @see org.lanyonm.grabbag.dao.UsersDao#getUsers()
	 */
	public List<User> getUsers() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<Map<String, Object>> ret = template.queryForList("select * from user;");
		List<User> users = new ArrayList<User>();
		for (Map<String, Object> row : ret) {
			users.add(new User(row.get("FIRST_NAME").toString(), row.get("LAST_NAME").toString(), row.get("EMAIL").toString()));
		}
		return users;
	}
}
