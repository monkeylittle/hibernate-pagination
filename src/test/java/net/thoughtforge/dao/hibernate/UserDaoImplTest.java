package net.thoughtforge.dao.hibernate;

import net.thoughtforge.dao.UserDao;
import net.thoughtforge.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContext/applicationContext-*.xml"
})
@Transactional
public class UserDaoImplTest {

	private static final String FIRST_NAME = "joe";
	
	private static final String LAST_NAME = "bloggs";
	
	private static final String PASSWORD = "password";
	
	private static final String USERNAME = "joe.bloggs";

	@Autowired
	private UserDao userDao;
	
	@Before
	public void before() {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setPassword(PASSWORD);
		user.setUsername(USERNAME);
		
		userDao.save(user);
	}
	
	@Test
	public void findByUserName() {
		User user = userDao.findByUsername(USERNAME);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(USERNAME, user.getUsername());
	}
}
