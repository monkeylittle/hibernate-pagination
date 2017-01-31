package net.thoughtforge.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import net.thoughtforge.dao.PersistentEntityDao;
import net.thoughtforge.model.Page;
import net.thoughtforge.model.PageImpl;
import net.thoughtforge.model.PageRequest;
import net.thoughtforge.model.PersistentEntity;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class PersistentEntityDaoImpl<Entity extends PersistentEntity> implements
        PersistentEntityDao<Entity> {

    private Class<Entity> entityClass;

    private HibernateTemplate hibernateTemplate;

    @SuppressWarnings(value = "unchecked")
    public PersistentEntityDaoImpl(final HibernateTemplate hibernateTemplate) {

        this.hibernateTemplate = hibernateTemplate;

        ParameterizedType genericSuperclass =
                (ParameterizedType) getClass().getGenericSuperclass();
        
        this.entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
    }

    public final Entity findByIndexId(final Integer indexId) {

        return (Entity) hibernateTemplate.get(entityClass, indexId);
    }

    @SuppressWarnings("unchecked")
    public final List<Entity> findByNamedQuery(final String queryName, final Object... params) {

        return (List<Entity>) hibernateTemplate.findByNamedQuery(queryName, params);
    }

    public final Page<Entity> findByNamedQuery(final PageRequest pageRequest,
            final String queryName, final Object ...params) {
        
        return hibernateTemplate.execute(new HibernateCallback<Page<Entity>>() {

            @SuppressWarnings("unchecked")
            @Override
            public Page<Entity> doInHibernate(final Session session) throws SQLException {

                long totalNumberOfElements = getRowCount(session, queryName, params);
                
                Query query = getNamedQuery(session, queryName);
                
                for (int index = 0; index < params.length; index++) {
                
                    query.setParameter(index, params[index]);
                }
                
                int firstResult = (pageRequest.getPageNumber() - 1) * pageRequest.getPageSize();
                int maxResults = pageRequest.getPageSize();
                
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                
                List<Entity> contents = query.list();
                
                return new PageImpl<Entity>(
                        contents,
                        pageRequest.getPageNumber(),
                        pageRequest.getPageSize(),
                        totalNumberOfElements);
            }
        });
    }
    
    public final Entity findUniqueByNamedQuery(final String queryName, final Object... params) {

        List<Entity> results = findByNamedQuery(queryName, params);

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public final void refresh(final Entity persistentEntity) {

        hibernateTemplate.refresh(persistentEntity);
    }

    public final void remove(final Entity persistentEntity) {

        hibernateTemplate.delete(persistentEntity);
    }

    public final Entity save(final Entity persistentEntity) {

        if (persistentEntity.getId() == null) {
            hibernateTemplate.persist(persistentEntity);
            
            return persistentEntity;
        } else {
            return (Entity) hibernateTemplate.merge(persistentEntity);
        }
    }
    
    private Query getNamedQuery(final Session session, final String queryName) {
        
        try {
            return session.getNamedQuery(queryName);
        } catch (MappingException mappingException) {
            
            return null;
        }
    }
    
    private long getRowCount(final Session session, final String queryName, final Object... params)
            throws SQLException {
        
        String rowCountQueryName = queryName + ".count";

        Query rowCountQuery = getNamedQuery(session, rowCountQueryName);
        if (rowCountQuery == null) {
    
            rowCountQuery = getNamedQuery(session, queryName);
            
            setParameters(rowCountQuery, params);
    
            return rowCountQuery.list().size();
        } else {
    
            setParameters(rowCountQuery, params);
    
            return (Long) rowCountQuery.uniqueResult();
        }
    }

    private void setParameters(final Query query, final Object... params) {

        for (int index = 0; index < params.length; index++) {
    
            query.setParameter(index, params[index]);
        }
    }
}
