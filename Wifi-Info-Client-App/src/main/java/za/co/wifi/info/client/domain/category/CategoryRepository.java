package za.co.wifi.info.client.domain.category;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.client.domain.DBOperationFailedException;

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
 * @version 1.0
 */
@Repository
@Transactional
public class CategoryRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public CategoryEntity save(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.save(categoryDTO);
    }

    public CategoryEntity update(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.update(categoryDTO);
    }

    public CategoryEntity find(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.find(categoryDTO);
    }

    public List<CategoryEntity> findAll(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.findAll(categoryDTO);
    }
}
