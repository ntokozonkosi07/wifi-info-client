package za.co.wifi.info.data.category;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import za.co.wifi.info.data.DBOperationFailedException;
import za.co.wifi.info.util.Validator;

/**
 * Copyright (C) 2014 RynMag Management Systems. All rights reserved.
 *
 * This software contains confidential proprietary information belonging to
 * RynMag Management Systems. No part of this information may be used,
 * reproduced, or stored without prior written consent of RynMag Management
 * Systems.
 *
 * @author Zifa Mathebula <zifamathebula@gmail.com>
 *
 * @version 1.0 1 September, 2016
 */
public class CategoryDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public Category save(Category categoryDTO) throws DBOperationFailedException {
        try {
            em.persist(categoryDTO);
        } catch (Exception ex) {
            throw new DBOperationFailedException("Error saving category : " + ex.getMessage());
        }

        return categoryDTO;
    }

    public Category update(Category categoryDTO) throws DBOperationFailedException {
        try {
            em.merge(categoryDTO);
        } catch (Exception ex) {
            throw new DBOperationFailedException("Error updating category detail : " + ex.getMessage());
        }

        return categoryDTO;
    }

    public Category find(Category categoryDTO) throws DBOperationFailedException {
        try {
            Query retval = buildSearch(categoryDTO);
            return (Category) retval.getSingleResult();
        } catch (Exception ex) {
            throw new DBOperationFailedException("No results found : " + ex.getMessage());
        }
    }

    public List<Category> findAll(Category categoryDTO) throws DBOperationFailedException {
        List<Category> allCategories = null;

        try {
            Query retval = buildSearch(categoryDTO);
            allCategories = retval.getResultList();
        } catch (Exception ex) {
            allCategories = new LinkedList();
        }

        return allCategories;
    }

    @SuppressWarnings("unused")
    private boolean assessAppend(StringBuilder buff, boolean first) {
        if (first) {
            buff.append(" WHERE ");
            return false;
        } else {
            buff.append(" AND ");
            return first;
        }
    }

    @SuppressWarnings("unused")
    private Query buildSearch(Category categoryDTO) throws DBOperationFailedException {
        try {
            Query retval = null;
            StringBuilder buff = new StringBuilder();

            buff.append("SELECT u FROM za.co.rynmag.wifiinfo.domain.entities.Category u");

            boolean first = true;
            int index = 1;

            if (!Validator.getInstance().isEmpty(categoryDTO.getCategoryRef())) {
                first = this.assessAppend(buff, first);

                buff.append("u.categoryRef =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(categoryDTO.getCategoryName())) {
                first = this.assessAppend(buff, first);

                buff.append("u.categoryName =?");
                buff.append(index++);
            }

            buff.append(" ORDER BY u.categoryName ASC");
            retval = em.createQuery(buff.toString());
            index = 1;

            if (!Validator.getInstance().isEmpty(categoryDTO.getCategoryRef())) {
                retval.setParameter(index++, categoryDTO.getCategoryRef());
            }
            if (!Validator.getInstance().isEmpty(categoryDTO.getCategoryName())) {
                retval.setParameter(index++, categoryDTO.getCategoryName());
            }

            return retval;
        } catch (Exception ex) {
            throw new DBOperationFailedException("Failed to build query : " + ex.getMessage());
        }
    }
}
