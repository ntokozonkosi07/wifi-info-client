package za.co.wifi.info.repository;

import java.io.Serializable;

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
 * @version 1.0 1 August, 2014.
 */
public class DBOperationFailedException extends Throwable implements Serializable {

    private static final long serialVersionUID = 1L;

    public DBOperationFailedException() {
        super();
    }

    public DBOperationFailedException(String message) {
        super(message);
    }

    public DBOperationFailedException(Exception ex) {
        super(ex);
    }

    public DBOperationFailedException(String message, Exception ex) {
        super(message, ex);
    }
}
