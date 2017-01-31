package net.thoughtforge.dao.hibernate;

import net.thoughtforge.dao.UserDao;
import net.thoughtforge.model.Page;
import net.thoughtforge.model.PageRequest;
import net.thoughtforge.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends PersistentEntityDaoImpl<User> implements UserDao {

    @Autowired
    public UserDaoImpl(HibernateTemplate hibernateTemplate) {

        super(hibernateTemplate);
    }

    public Page<User> findByLastName(PageRequest pageRequest, String lastName) {
        
        return findByNamedQuery(pageRequest, "user.findByLastName", lastName + '%');
    }
    
    public User findByUsername(String username) {

        return findUniqueByNamedQuery("user.findByUsername", username);
    }
}
