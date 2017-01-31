package net.thoughtforge.dao.hibernate;

import net.thoughtforge.dao.UserGroupDao;
import net.thoughtforge.model.UserGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserGroupDaoImpl extends PersistentEntityDaoImpl<UserGroup> implements UserGroupDao {

    @Autowired
    public UserGroupDaoImpl(HibernateTemplate hibernateTemplate) {

        super(hibernateTemplate);
    }

    public UserGroup findByName(String name) {

        return findUniqueByNamedQuery("userGroup.findByName", name);
    }
}
