package za.co.wifi.info.data.advert;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.data.DBOperationFailedException;
import za.co.wifi.info.data.category.Category;

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
public class AdvertManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public Advert save(Advert advertDTO) throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.save(advertDTO);
    }

    public Advert update(Advert advertDTO) throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.update(advertDTO);
    }

    public Advert find(Advert advertDTO) throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.find(advertDTO);
    }

    public List<Advert> findAll(Advert advertDTO) throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.findAll(advertDTO);
    }

    public List<Advert> findAll(List<AdvertType> advertTypes) throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.findAll(advertTypes);
    }

    public List<Advert> findAll(Category category, List<AdvertType> advertTypes) throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.findAll(category, advertTypes);
    }

    public DownloadPage findDownloadPage() throws DBOperationFailedException {
        AdvertDAO advert = new AdvertDAO(em);
        return advert.findDownloadPage();
    }
}
