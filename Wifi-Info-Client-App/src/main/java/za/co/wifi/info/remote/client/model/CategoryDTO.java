package za.co.wifi.info.remote.client.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("createdBy")
    private Long createdBy;

    @JsonProperty("createdDate")
    private Long createdDate;

    @JsonProperty("lastModifiedBy")
    private Object lastModifiedBy;

    @JsonProperty("lastModifiedDate")
    private Object lastModifiedDate;

    @JsonProperty("categoryRef")
    private Long categoryRef;

    @JsonProperty("categoryOwner")
    private Object categoryOwner;

    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("allSubCategories")
    private Object allSubCategories;

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryDTO() {
    }

    /**
     *
     * @param categoryName
     * @param createdBy
     * @param lastModifiedBy
     * @param lastModifiedDate
     * @param categoryRef
     * @param createdDate
     * @param allSubCategories
     * @param categoryOwner
     */
    public CategoryDTO(Long createdBy, Long createdDate, Object lastModifiedBy, Object lastModifiedDate, Long categoryRef, Object categoryOwner, String categoryName, Object allSubCategories) {
        super();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.categoryRef = categoryRef;
        this.categoryOwner = categoryOwner;
        this.categoryName = categoryName;
        this.allSubCategories = allSubCategories;
    }

    @JsonProperty("createdBy")
    public Long getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("createdDate")
    public Long getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("lastModifiedBy")
    public Object getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty("lastModifiedBy")
    public void setLastModifiedBy(Object lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @JsonProperty("lastModifiedDate")
    public Object getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonProperty("lastModifiedDate")
    public void setLastModifiedDate(Object lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonProperty("categoryRef")
    public Long getCategoryRef() {
        return categoryRef;
    }

    @JsonProperty("categoryRef")
    public void setCategoryRef(Long categoryRef) {
        this.categoryRef = categoryRef;
    }

    @JsonProperty("categoryOwner")
    public Object getCategoryOwner() {
        return categoryOwner;
    }

    @JsonProperty("categoryOwner")
    public void setCategoryOwner(Object categoryOwner) {
        this.categoryOwner = categoryOwner;
    }

    @JsonProperty("categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    @JsonProperty("categoryName")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonProperty("allSubCategories")
    public Object getAllSubCategories() {
        return allSubCategories;
    }

    @JsonProperty("allSubCategories")
    public void setAllSubCategories(Object allSubCategories) {
        this.allSubCategories = allSubCategories;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(createdBy)
                .append(createdDate)
                .append(lastModifiedBy)
                .append(lastModifiedDate)
                .append(categoryRef)
                .append(categoryOwner)
                .append(categoryName)
                .append(allSubCategories)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CategoryDTO) == false) {
            return false;
        }
        CategoryDTO rhs = ((CategoryDTO) other);
        return new EqualsBuilder().append(createdBy, rhs.createdBy)
                .append(createdDate, rhs.createdDate)
                .append(lastModifiedBy, rhs.lastModifiedBy)
                .append(lastModifiedDate, rhs.lastModifiedDate)
                .append(categoryRef, rhs.categoryRef)
                .append(categoryOwner, rhs.categoryOwner)
                .append(categoryName, rhs.categoryName)
                .append(allSubCategories, rhs.allSubCategories)
                .isEquals();
    }
}
