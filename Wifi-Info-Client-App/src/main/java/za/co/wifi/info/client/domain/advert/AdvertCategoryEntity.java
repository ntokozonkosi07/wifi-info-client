package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import za.co.wifi.info.client.domain.advert.AdvertEntity;
import za.co.wifi.info.client.domain.BaseEntity;
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
@Entity(name = "l_advert_category")
public class AdvertCategoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "advert_category_ref")
    @GeneratedValue(generator = "advert_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advert_category", sequenceName = "advert_category_seq", allocationSize = 1, initialValue = 1)
    private Long advertCategoryRef;

    @ManyToOne
    @JoinColumn(name = "advert_ref", nullable = false)
    private AdvertEntity advertRef;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_ref", nullable = false)
    private CategoryEntity categoryRef;

    public Long getAdvertCategoryRef() {
        return advertCategoryRef;
    }

    public void setAdvertCategoryRef(Long advertCategoryRef) {
        this.advertCategoryRef = advertCategoryRef;
    }

    public AdvertEntity getAdvertRef() {
        return advertRef;
    }

    public void setAdvertRef(AdvertEntity advertRef) {
        this.advertRef = advertRef;
    }

    public CategoryEntity getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(CategoryEntity categoryRef) {
        this.categoryRef = categoryRef;
    }
}
