package za.co.wifi.info.remote.client.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HOInfoDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("status")
    private String status;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("osName")
    private String osName;

    @JsonProperty("osVersion")
    private String osVersion;

    @JsonProperty("osArchitecture")
    private String osArchitecture;

    @JsonProperty("availableProcessors")
    private Integer availableProcessors;

    @JsonProperty("freeMemory")
    private Integer freeMemory;

    @JsonProperty("maxMemory")
    private String maxMemory;

    @JsonProperty("availableMemory")
    private Integer availableMemory;

    /**
     * No args constructor for use in serialization
     */
    public HOInfoDTO() {
    }

    /**
     * @param timestamp
     * @param osVersion
     * @param availableMemory
     * @param status
     * @param availableProcessors
     * @param osArchitecture
     * @param maxMemory
     * @param freeMemory
     * @param osName
     */
    public HOInfoDTO(String status, String timestamp, String osName, String osVersion, String osArchitecture, Integer availableProcessors, Integer freeMemory, String maxMemory, Integer availableMemory) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArchitecture = osArchitecture;
        this.availableProcessors = availableProcessors;
        this.freeMemory = freeMemory;
        this.maxMemory = maxMemory;
        this.availableMemory = availableMemory;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("osName")
    public String getOsName() {
        return osName;
    }

    @JsonProperty("osName")
    public void setOsName(String osName) {
        this.osName = osName;
    }

    @JsonProperty("osVersion")
    public String getOsVersion() {
        return osVersion;
    }

    @JsonProperty("osVersion")
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @JsonProperty("osArchitecture")
    public String getOsArchitecture() {
        return osArchitecture;
    }

    @JsonProperty("osArchitecture")
    public void setOsArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
    }

    @JsonProperty("availableProcessors")
    public Integer getAvailableProcessors() {
        return availableProcessors;
    }

    @JsonProperty("availableProcessors")
    public void setAvailableProcessors(Integer availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    @JsonProperty("freeMemory")
    public Integer getFreeMemory() {
        return freeMemory;
    }

    @JsonProperty("freeMemory")
    public void setFreeMemory(Integer freeMemory) {
        this.freeMemory = freeMemory;
    }

    @JsonProperty("maxMemory")
    public String getMaxMemory() {
        return maxMemory;
    }

    @JsonProperty("maxMemory")
    public void setMaxMemory(String maxMemory) {
        this.maxMemory = maxMemory;
    }

    @JsonProperty("availableMemory")
    public Integer getAvailableMemory() {
        return availableMemory;
    }

    @JsonProperty("availableMemory")
    public void setAvailableMemory(Integer availableMemory) {
        this.availableMemory = availableMemory;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status)
                .append(timestamp)
                .append(osName)
                .append(osVersion)
                .append(osArchitecture)
                .append(availableProcessors)
                .append(freeMemory)
                .append(maxMemory)
                .append(availableMemory)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HOInfoDTO) == false) {
            return false;
        }
        HOInfoDTO rhs = ((HOInfoDTO) other);
        return new EqualsBuilder().append(status, rhs.status)
                .append(timestamp, rhs.timestamp)
                .append(osName, rhs.osName)
                .append(osVersion, rhs.osVersion)
                .append(osArchitecture, rhs.osArchitecture)
                .append(availableProcessors, rhs.availableProcessors)
                .append(freeMemory, rhs.freeMemory)
                .append(maxMemory, rhs.maxMemory)
                .append(availableMemory, rhs.availableMemory)
                .isEquals();
    }
}
