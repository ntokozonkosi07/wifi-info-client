package za.co.wifi.info.data.category;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.data.DBOperationFailedException;

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
public class CategoryManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public Category save(Category categoryDTO) throws DBOperationFailedException {
        CategoryDAO categry = new CategoryDAO(em);
        return categry.save(categoryDTO);
    }

    public Category update(Category categoryDTO) throws DBOperationFailedException {
        CategoryDAO categry = new CategoryDAO(em);
        return categry.update(categoryDTO);
    }

    public Category find(Category categoryDTO) throws DBOperationFailedException {
        CategoryDAO categry = new CategoryDAO(em);
        return categry.find(categoryDTO);
    }

    public List<Category> findAll(Category categoryDTO) throws DBOperationFailedException {
        CategoryDAO categry = new CategoryDAO(em);
        return categry.findAll(categoryDTO);
    }
}
