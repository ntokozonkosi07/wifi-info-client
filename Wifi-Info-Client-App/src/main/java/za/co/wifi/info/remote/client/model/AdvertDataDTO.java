package za.co.wifi.info.remote.client.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertDataDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("binaryData")
    private byte[] binaryData;

    @JsonProperty("fileType")
    private String fileType;

    @JsonProperty("fileName")
    private String fileName;

    /**
     * No args constructor for use in serialization
     */
    public AdvertDataDTO() {
    }

    /**
     * @param fileType
     * @param fileName
     * @param binaryData
     */
    public AdvertDataDTO(byte[] binaryData, String fileType, String fileName) {
        super();
        this.binaryData = binaryData;
        this.fileType = fileType;
        this.fileName = fileName;
    }

    @JsonProperty("binaryData")
    public byte[] getBinaryData() {
        return binaryData;
    }

    @JsonProperty("binaryData")
    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    @JsonProperty("fileType")
    public String getFileType() {
        return fileType;
    }

    @JsonProperty("fileType")
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @JsonProperty("fileName")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(binaryData)
                .append(fileType)
                .append(fileName)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdvertDataDTO) == false) {
            return false;
        }
        AdvertDataDTO rhs = ((AdvertDataDTO) other);
        return new EqualsBuilder().append(binaryData, rhs.binaryData)
                .append(fileType, rhs.fileType)
                .append(fileName, rhs.fileName)
                .isEquals();
    }
}
