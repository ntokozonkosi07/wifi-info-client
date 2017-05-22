package za.co.wifi.info.client.web.model;

import java.io.Serializable;
import java.util.Objects;

public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    private String advertBinaryData;

    private String fileName;

    private String fileType;

    private long fileSize;

    public File() {
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

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.fileName);
        hash = 37 * hash + Objects.hashCode(this.fileType);
        hash = 37 * hash + (int) (this.fileSize ^ (this.fileSize >>> 32));
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
        final File other = (File) obj;
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        if (!Objects.equals(this.fileType, other.fileType)) {
            return false;
        }
        return true;
    }
}
