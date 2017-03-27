package za.co.wifi.info.util;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

/**
 * Copyright (C) 2014 RynMag Management Systems. All rights reserved.
 *
 * This software contains confidential proprietary information belonging to
 * RynMag Management Systems. No part of this information may be used,
 * reproduced, or stored without prior written consent of RynMag Management
 * Systems.
 *
 * @author Z.M Mathebula <zifamathebula@gmail.com>
 *
 * @version 1.0
 */
public class Validator implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Validator validationUtil = null;

    protected Validator() {
        super();
    }

    public static Validator getInstance() {
        if (validationUtil == null) {
            validationUtil = new Validator();
        }
        return validationUtil;
    }

    public boolean startDateAfterEndDate(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            return true;
        }
        return false;
    }

    public boolean startDateBeforeEndDate(Date startDate, Date endDate) {
        if (startDate.before(endDate)) {
            return true;
        }
        return false;
    }

    public boolean isWhitespace(String value) {
        return value.matches("^\\s*$");
    }

    public boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public boolean isEmpty(Date value) {
        return value == null;
    }

    public boolean isEmpty(List value) {
        return value == null || value.isEmpty();
    }

    public boolean isEmpty(Object value) {
        return value == null;
    }

    public boolean isEmpty(Long value) {
        return value == null || value.equals(0L);
    }

    public boolean isEmpty(long value) {
        return value == 0;
    }

    public boolean isEmpty(Integer value) {
        return value == null || value.equals(0L);
    }

    public boolean isEmpty(int value) {
        return value == 0;
    }

    public boolean isEmpty(Double value) {
        return value == null || value.equals(0L);
    }

    public boolean isEmpty(double value) {
        return value == 0;
    }
}
