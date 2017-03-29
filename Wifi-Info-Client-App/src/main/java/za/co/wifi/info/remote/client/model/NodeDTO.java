package za.co.wifi.info.remote.client.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeDTO implements Serializable {

    private final static long serialVersionUID = 1L;
    
    @JsonProperty("deviceRef")
    private String deviceRef;

    @JsonProperty("macAddress")
    private String macAddress;

    @JsonProperty("nodeCategory")
    private Integer nodeCategory;

    @JsonProperty("nodeCategoryName")
    private String nodeCategoryName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    /**
     * No args constructor for use in serialization
     *
     */
    public NodeDTO() {
    }

    /**
     *
     * @param nodeCategory
     * @param deviceRef
     * @param address
     * @param name
     * @param nodeCategoryName
     * @param macAddress
     */
    public NodeDTO(String deviceRef, String macAddress, Integer nodeCategory, String nodeCategoryName, String name, String address) {
        super();
        this.deviceRef = deviceRef;
        this.macAddress = macAddress;
        this.nodeCategory = nodeCategory;
        this.nodeCategoryName = nodeCategoryName;
        this.name = name;
        this.address = address;
    }

    @JsonProperty("deviceRef")
    public String getDeviceRef() {
        return deviceRef;
    }

    @JsonProperty("deviceRef")
    public void setDeviceRef(String deviceRef) {
        this.deviceRef = deviceRef;
    }

    @JsonProperty("macAddress")
    public String getMacAddress() {
        return macAddress;
    }

    @JsonProperty("macAddress")
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @JsonProperty("nodeCategory")
    public Integer getNodeCategory() {
        return nodeCategory;
    }

    @JsonProperty("nodeCategory")
    public void setNodeCategory(Integer nodeCategory) {
        this.nodeCategory = nodeCategory;
    }

    @JsonProperty("nodeCategoryName")
    public String getNodeCategoryName() {
        return nodeCategoryName;
    }

    @JsonProperty("nodeCategoryName")
    public void setNodeCategoryName(String nodeCategoryName) {
        this.nodeCategoryName = nodeCategoryName;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceRef)
                .append(macAddress)
                .append(nodeCategory)
                .append(nodeCategoryName)
                .append(name)
                .append(address)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NodeDTO) == false) {
            return false;
        }
        NodeDTO rhs = ((NodeDTO) other);
        return new EqualsBuilder().append(deviceRef, rhs.deviceRef)
                .append(macAddress, rhs.macAddress)
                .append(nodeCategory, rhs.nodeCategory)
                .append(nodeCategoryName, rhs.nodeCategoryName)
                .append(name, rhs.name)
                .append(address, rhs.address)
                .isEquals();
    }
}
