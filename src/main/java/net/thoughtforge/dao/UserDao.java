package net.thoughtforge.dao;

import net.thoughtforge.model.Page;
import net.thoughtforge.model.PageRequest;
import net.thoughtforge.model.User;

public interface UserDao extends PersistentEntityDao<User> {

    Page<User> findByLastName(PageRequest pageRequest, String lastName);
    
    User findByUsername(String username);
}