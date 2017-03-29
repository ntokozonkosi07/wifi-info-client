package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
@Entity(name = "l_download_page")
public class DownloadPageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "download_page_ref")
    @GeneratedValue(generator = "download_page", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "download_page", sequenceName = "download_page_seq", allocationSize = 1, initialValue = 1)
    private Long downloadPageRef;
    
    @Column(name = "binary_data", nullable = false)
    private byte[] downloadPageData;

    public Long getDownloadPageRef() {
        return downloadPageRef;
    }

    public void setDownloadPageRef(Long downloadPageRef) {
        this.downloadPageRef = downloadPageRef;
    }

    public byte[] getDownloadPageData() {
        return downloadPageData;
    }

    public void setDownloadPageData(byte[] downloadPageData) {
        this.downloadPageData = downloadPageData;
    }

    
}
