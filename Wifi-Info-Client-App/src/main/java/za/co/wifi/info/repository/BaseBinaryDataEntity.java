package za.co.wifi.info.repository;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

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
@MappedSuperclass
public class BaseBinaryDataEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "binary_data", nullable = false)
    private byte[] advertBinaryData;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    public byte[] getBinaryData() {
        return advertBinaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.advertBinaryData = binaryData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
