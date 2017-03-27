package za.co.wifi.info.data.category;

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
import za.co.wifi.info.data.advert.Advert;
import za.co.wifi.info.data.BaseEntity;

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
public class AdvertCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "advert_category_ref")
    @GeneratedValue(generator = "advert_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advert_category", sequenceName = "advert_category_seq", allocationSize = 1, initialValue = 1)
    private Long advertCategoryRef;

    @ManyToOne
    @JoinColumn(name = "advert_ref", nullable = false)
    private Advert advertRef;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_ref", nullable = false)
    private Category categoryRef;

    public Long getAdvertCategoryRef() {
        return advertCategoryRef;
    }

    public void setAdvertCategoryRef(Long advertCategoryRef) {
        this.advertCategoryRef = advertCategoryRef;
    }

    public Advert getAdvertRef() {
        return advertRef;
    }

    public void setAdvertRef(Advert advertRef) {
        this.advertRef = advertRef;
    }

    public Category getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(Category categoryRef) {
        this.categoryRef = categoryRef;
    }
}
