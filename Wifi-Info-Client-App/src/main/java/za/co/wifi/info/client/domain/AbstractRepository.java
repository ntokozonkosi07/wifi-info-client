package za.co.wifi.info.client.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public abstract class AbstractRepository<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract EntityManager getEntityManager();

    public T save(T entity) throws DBOperationFailedException {
        try {
            this.getEntityManager().persist(entity);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
        return entity;
    }

    public T update(T entity) throws DBOperationFailedException {
        T returnableEntity = null;

        try {
            returnableEntity = this.getEntityManager().merge(entity);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }

        return returnableEntity;
    }

    public abstract T find(T entity) throws DBOperationFailedException;

    public abstract List<T> findAll(T entity) throws DBOperationFailedException;
}
