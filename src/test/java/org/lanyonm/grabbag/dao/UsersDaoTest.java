package org.lanyonm.grabbag.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.lanyonm.grabbag.config.DataConfig;
import org.lanyonm.grabbag.dao.jdbc.UsersDaoImpl;
import org.lanyonm.grabbag.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={DataConfig.class, UsersDaoImpl.class})
public class UsersDaoTest {

	@Autowired
	private UsersDao usersDao;
	
//	private EmbeddedDatabase db;

	@Before
	public void setup() {
//		db = new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.addScript("schema.sql")
//				.addScript("init.sql")
//				.build();
	}

	@After
    public void tearDown() {
//        db.shutdown();
    }

	@Test
	public void testGetUsers() {
//		JdbcTemplate template = new JdbcTemplate(db);
//		List<Map<String, Object>> users = template.queryForList("select * from user;");
		List<User> users = usersDao.getUsers();
		assertNotNull(users);
		assertEquals("number of users", 2, users.size());
		assertEquals("second user's id should be 2", 2, users.get(1).getId());
		assertEquals("first user's first name should be Mike", "Mike", users.get(0).getFirstName());
	}
}
