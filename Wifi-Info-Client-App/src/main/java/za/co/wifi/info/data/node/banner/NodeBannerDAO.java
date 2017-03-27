package za.co.wifi.info.data.node.banner;

import java.io.Serializable;
import javax.persistence.EntityManager;
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
public class NodeBannerDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public NodeBannerDAO(EntityManager em) {
        this.em = em;
    }

    public NodeBanner save(NodeBanner nodeBanner) throws DBOperationFailedException {
        try {
            em.persist(nodeBanner);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
        return nodeBanner;
    }

    public NodeBanner update(NodeBanner nodeBanner) throws DBOperationFailedException {
        try {
            return em.merge(nodeBanner);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }

    public NodeBanner findActiveNodeBanner() throws DBOperationFailedException {
        return null;
    }
}
