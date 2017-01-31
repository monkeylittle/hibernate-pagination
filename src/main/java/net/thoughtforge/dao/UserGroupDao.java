package net.thoughtforge.dao;

import net.thoughtforge.model.UserGroup;

public interface UserGroupDao extends PersistentEntityDao<UserGroup> {

    UserGroup findByName(String name);
}