package za.co.wifi.info.data.node;

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
public class NodeManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public Node save(Node node) throws DBOperationFailedException {
        NodeDAO iNode = new NodeDAO(em);
        return iNode.save(node);
    }

    public Node update(Node node) throws DBOperationFailedException {
        NodeDAO iNode = new NodeDAO(em);
        return iNode.update(node);
    }

    public Node find(Node node) throws DBOperationFailedException {
        NodeDAO iNode = new NodeDAO(em);
        return iNode.find(node);
    }

    public List<Node> findAll(Node node) throws DBOperationFailedException {
        NodeDAO iNode = new NodeDAO(em);
        return iNode.findAll(node);
    }
}
