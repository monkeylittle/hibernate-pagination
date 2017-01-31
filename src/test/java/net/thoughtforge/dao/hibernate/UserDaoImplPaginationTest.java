package net.thoughtforge.dao.hibernate;

import net.thoughtforge.dao.UserDao;
import net.thoughtforge.model.Page;
import net.thoughtforge.model.PageRequest;
import net.thoughtforge.model.PageRequestImpl;
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
public class UserDaoImplPaginationTest {

	@Autowired
	private UserDao userDao;
	
	@Before
	public void before() {
		for (int i = 0; i < 45; i++) {
		    createUser("firstName" + 1, "lastName" + i, "password", "username" + i);
		}
		
        for (int i = 0; i < 10; i++) {
            createUser("firstName" + 1, "anotherLastName" + i, "password", "username" + i);
        }
	}
	
	@Test
	public void findFirstPageByLastName() {
		PageRequest pageRequest = new PageRequestImpl(1, 10);
		
		Page<User> page = userDao.findByLastName(pageRequest, "lastName");
		
		Assert.assertNotNull(page);
		Assert.assertEquals(45, page.getTotalNumberOfElements());
		Assert.assertEquals(5, page.getTotalPages());
		Assert.assertEquals(10, page.getPageSize());
		Assert.assertEquals(1, page.getPageNumber());
		Assert.assertEquals(10, page.getNumberOfElements());
		Assert.assertNotNull(page.getContent());
		Assert.assertEquals(10, page.getContent().size());
	}
	
    @Test
    public void findThirdPageByLastName() {
        PageRequest pageRequest = new PageRequestImpl(3, 10);
        
        Page<User> page = userDao.findByLastName(pageRequest, "lastName");
        
        Assert.assertNotNull(page);
        Assert.assertEquals(45, page.getTotalNumberOfElements());
        Assert.assertEquals(5, page.getTotalPages());
        Assert.assertEquals(10, page.getPageSize());
        Assert.assertEquals(3, page.getPageNumber());
        Assert.assertEquals(10, page.getNumberOfElements());
        Assert.assertNotNull(page.getContent());
        Assert.assertEquals(10, page.getContent().size());
    }
    
    @Test
    public void findLastPageByLastName() {
        PageRequest pageRequest = new PageRequestImpl(5, 10);
        
        Page<User> page = userDao.findByLastName(pageRequest, "lastName");
        
        Assert.assertNotNull(page);
        Assert.assertEquals(45, page.getTotalNumberOfElements());
        Assert.assertEquals(5, page.getTotalPages());
        Assert.assertEquals(10, page.getPageSize());
        Assert.assertEquals(5, page.getPageNumber());
        Assert.assertEquals(5, page.getNumberOfElements());
        Assert.assertNotNull(page.getContent());
        Assert.assertEquals(5, page.getContent().size());
    }
	
	private void createUser(String firstName, String lastName, String password, String username) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setUsername(username);
        
        userDao.save(user);
	}
}
