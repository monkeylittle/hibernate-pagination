package net.thoughtforge.dao;

import net.thoughtforge.model.UserToken;

public interface UserTokenDao extends PersistentEntityDao<UserToken> {

    UserToken findByName(String name);
}
