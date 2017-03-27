package za.co.wifi.info.controller.model;

import java.io.Serializable;

import java.util.Objects;

public class Advert implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long advertRef;

    private String advertBinaryData;

    private String fileName;

    private String fileType;

    private Long fileSize;

    public Advert() {
    }

    public Long getAdvertRef() {
        return advertRef;
    }

    public void setAdvertRef(Long advertRef) {
        this.advertRef = advertRef;
    }

    public String getAdvertBinaryData() {
        return advertBinaryData;
    }

    public void setAdvertBinaryData(String advertBinaryData) {
        this.advertBinaryData = advertBinaryData;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.advertRef);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Advert other = (Advert) obj;
        if (!Objects.equals(this.advertRef, other.advertRef)) {
            return false;
        }
        return true;
    }
}
