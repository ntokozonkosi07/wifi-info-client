package za.co.wifi.info.data.node.banner;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class NodeBannerManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public NodeBanner saveNodeBanner(NodeBanner nodeBanner) throws DBOperationFailedException {
        return null;
    }

    public NodeBanner findActiveNodeBanner() throws DBOperationFailedException {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT n FROM za.co.rynmag.wifiinfo.domain.entities.NodeBanner n ");
            buff.append("WHERE :currentDate BETWEEN n.startDate AND  n.endDate");

            Query retval = em.createQuery(buff.toString());
            retval.setParameter("currentDate", new Date());

            return (NodeBanner) retval.getSingleResult();
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }
}
