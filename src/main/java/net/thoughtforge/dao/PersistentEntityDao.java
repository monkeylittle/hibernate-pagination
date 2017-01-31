package net.thoughtforge.dao;

import java.util.List;

import net.thoughtforge.model.Page;
import net.thoughtforge.model.PageRequest;
import net.thoughtforge.model.PersistentEntity;

public interface PersistentEntityDao<Entity extends PersistentEntity> {

    Entity findByIndexId(Integer indexId);
    
    List<Entity> findByNamedQuery(String queryName, Object ...params);
    
    Page<Entity> findByNamedQuery(PageRequest pageRequest, String queryName, Object ...params);
    
    Entity findUniqueByNamedQuery(String queryName, Object ...params);
    
    void refresh(Entity persistentEntity);
    
    void remove(Entity persistentEntity);
    
    Entity save(Entity persistentEntity);
}
