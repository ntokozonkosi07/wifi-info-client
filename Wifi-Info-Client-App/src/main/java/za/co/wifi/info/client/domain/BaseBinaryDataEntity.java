package za.co.wifi.info.client.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.hashCode(this.advertBinaryData);
        hash = 89 * hash + Objects.hashCode(this.fileName);
        hash = 89 * hash + Objects.hashCode(this.fileType);
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
        final BaseBinaryDataEntity other = (BaseBinaryDataEntity) obj;
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        if (!Objects.equals(this.fileType, other.fileType)) {
            return false;
        }
        if (!Arrays.equals(this.advertBinaryData, other.advertBinaryData)) {
            return false;
        }
        return true;
    }
}
