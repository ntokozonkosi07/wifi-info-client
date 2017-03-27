package za.co.wifi.info.data.node;

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
 * @version 1.0
 */
public class NodeDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public NodeDAO(EntityManager em) {
        this.em = em;
    }

    public Node save(Node node) throws DBOperationFailedException {
        try {
            em.persist(node);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getMessage());
        }
        return node;
    }

    public Node update(Node node) throws DBOperationFailedException {
        try {
            em.merge(node);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getMessage());
        }
        return node;
    }

    public Node find(Node node) throws DBOperationFailedException {
        try {
            Query retval = buildSearch(node);
            return (Node) retval.getSingleResult();
        } catch (Exception ex) {
            throw new DBOperationFailedException("Node not found");
        }
    }

    public List<Node> findAll(Node node) throws DBOperationFailedException {
        List<Node> allNodes = null;
        try {
            Query retval = buildSearch(node);
            allNodes = retval.getResultList();
        } catch (Exception ex) {
            allNodes = new LinkedList();
        }

        return allNodes;
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
    private Query buildSearch(Node node) throws DBOperationFailedException {
        try {
            Query retval = null;
            StringBuilder buff = new StringBuilder();

            buff.append("SELECT u FROM za.co.rynmag.wifiinfo.domain.entities.Node u");

            boolean first = true;
            int index = 1;

            if (!Validator.getInstance().isEmpty(node.getNodeRef())) {
                first = this.assessAppend(buff, first);

                buff.append("u.nodeRef =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(node.getMacAddress())) {
                first = this.assessAppend(buff, first);

                buff.append("u.macAddress =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(node.getDeviceRef())) {
                first = this.assessAppend(buff, first);

                buff.append("u.deviceRef =?");
                buff.append(index++);
            }

            retval = em.createQuery(buff.toString());
            index = 1;

            if (!Validator.getInstance().isEmpty(node.getNodeRef())) {
                retval.setParameter(index++, node.getNodeRef());
            }
            if (!Validator.getInstance().isEmpty(node.getMacAddress())) {
                retval.setParameter(index++, node.getMacAddress());
            }
            if (!Validator.getInstance().isEmpty(node.getDeviceRef())) {
                retval.setParameter(index++, node.getDeviceRef());
            }

            return retval;
        } catch (Exception ex) {
            throw new DBOperationFailedException("Failed to build query : " + ex.getMessage());
        }
    }
}
