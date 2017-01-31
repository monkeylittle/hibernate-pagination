package net.thoughtforge.dao.hibernate;

import net.thoughtforge.dao.UserTokenDao;
import net.thoughtforge.model.UserToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserTokenDaoImpl extends PersistentEntityDaoImpl<UserToken> implements UserTokenDao {

    @Autowired
    public UserTokenDaoImpl(HibernateTemplate hibernateTemplate) {

        super(hibernateTemplate);
    }

    public UserToken findByName(String name) {

        return findUniqueByNamedQuery("userToken.findByName", name);
    }
}
