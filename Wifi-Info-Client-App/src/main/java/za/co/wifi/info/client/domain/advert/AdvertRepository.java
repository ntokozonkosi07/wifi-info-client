package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.client.domain.DBOperationFailedException;
import za.co.wifi.info.client.domain.category.CategoryEntity;

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
public class AdvertRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public AdvertEntity save(AdvertEntity advertDTO) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.save(advertDTO);
    }

    public AdvertEntity update(AdvertEntity advertDTO) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.update(advertDTO);
    }

    public AdvertEntity find(AdvertEntity advertDTO) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.find(advertDTO);
    }

    public List<AdvertEntity> findAll(AdvertEntity advertDTO) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(advertDTO);
    }

    public List<AdvertEntity> findAll(List<AdvertType> advertTypes) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(advertTypes);
    }

    public List<AdvertEntity> findAll(CategoryEntity category, List<AdvertType> advertTypes) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(category, advertTypes);
    }

    public DownloadPageEntity findDownloadPage() throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findDownloadPage();
    }
}
