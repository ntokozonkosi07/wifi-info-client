package za.co.wifi.info.client.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Copyright (C) 2014 RynMag Management Systems. All rights reserved.
 *
 * This software contains confidential proprietary information belonging to
 * RynMag Management Systems. No part of this information may be used,
 * reproduced, or stored without prior written consent of RynMag Management
 * Systems.
 *
 * @author Z.M Mathebula <zifamathebula@gmail.com>
 *
 * @version 1.0
 */
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
