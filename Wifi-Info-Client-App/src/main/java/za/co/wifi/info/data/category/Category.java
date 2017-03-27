package za.co.wifi.info.data.category;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity(name = "l_category")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_ref")
    private Long categoryRef;

    @Column(name = "category_name", length = 80, nullable = false)
    private String categoryName;

    public Long getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(Long categoryRef) {
        this.categoryRef = categoryRef;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
