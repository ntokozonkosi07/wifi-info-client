package za.co.wifi.info.repository.advert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
 * @version 1.0 1 September, 2016
 */
public enum AdvertType implements Serializable {

    STANDARD(1, "Standard Advert"),
    HALF_PAGE(2, "Half Page Advert"),
    FULL_PAGE(3, "Full Page Advert"),
    LINK(4, "Banner Advert"),
    BANNER(5, "Banner Broadcaster");

    private final int id;
    private final String description;

    private AdvertType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static AdvertType getAdvertType(int id) {
        List<AdvertType> advertTypeEnums = Arrays.asList(values());
        for (AdvertType advertTypeEnum : advertTypeEnums) {
            if (advertTypeEnum.getId() == id) {
                return advertTypeEnum;
            }
        }
        return null;
    }

    public static List<AdvertType> getAllAdvertTypes() {
        return Arrays.asList(values());
    }
}
