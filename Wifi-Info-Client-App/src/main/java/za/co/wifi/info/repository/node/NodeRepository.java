package za.co.wifi.info.repository.node;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.repository.DBOperationFailedException;

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
public class NodeRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public NodeEntity save(NodeEntity node) throws DBOperationFailedException {
        NodeDAO nodeDAO = new NodeDAO(em);
        return nodeDAO.save(node);
    }

    public NodeEntity update(NodeEntity node) throws DBOperationFailedException {
        NodeDAO nodeDAO = new NodeDAO(em);
        return nodeDAO.update(node);
    }

    public NodeEntity find(NodeEntity node) throws DBOperationFailedException {
        NodeDAO nodeDAO = new NodeDAO(em);
        return nodeDAO.find(node);
    }

    public List<NodeEntity> findAll(NodeEntity node) throws DBOperationFailedException {
        NodeDAO nodeDAO = new NodeDAO(em);
        return nodeDAO.findAll(node);
    }
}
