package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import za.co.wifi.info.client.domain.BaseEntity;
import za.co.wifi.info.client.domain.category.AdvertCategoryEntity;

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
@Entity(name = "l_advert")
public class AdvertEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "advert_ref")
    @GeneratedValue(generator = "advert", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advert", sequenceName = "advert_seq", allocationSize = 1, initialValue = 1)
    private Long advertRef;

    @Column(name = "advert_ref_no", length = 255, nullable = true)
    private String advertRefNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "advert_type", nullable = false)
    private AdvertType adType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_from", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_to", nullable = true)
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_data_ref", referencedColumnName = "advert_data_ref", nullable = false)
    private AdvertDataEntity advertData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_link_data_ref", referencedColumnName = "advert_data_ref", nullable = true)
    private AdvertDataEntity advertLinkData;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "advert_ref")
    private List<AdvertCategoryEntity> advertCategories;

    public Long getAdvertRef() {
        return advertRef;
    }

    public void setAdvertRef(Long advertRef) {
        this.advertRef = advertRef;
    }

    public String getAdvertRefNo() {
        return advertRefNo;
    }

    public void setAdvertRefNo(String advertRefNo) {
        this.advertRefNo = advertRefNo;
    }

    public AdvertType getAdType() {
        return adType;
    }

    public void setAdType(AdvertType adType) {
        this.adType = adType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public AdvertDataEntity getAdvertData() {
        return advertData;
    }

    public void setAdvertData(AdvertDataEntity advertData) {
        this.advertData = advertData;
    }

    public AdvertDataEntity getAdvertLinkData() {
        return advertLinkData;
    }

    public void setAdvertLinkData(AdvertDataEntity advertLinkData) {
        this.advertLinkData = advertLinkData;
    }

    public List<AdvertCategoryEntity> getAdvertCategories() {
        return advertCategories;
    }

    public void setAdvertCategories(List<AdvertCategoryEntity> advertCategories) {
        this.advertCategories = advertCategories;
    }
}
