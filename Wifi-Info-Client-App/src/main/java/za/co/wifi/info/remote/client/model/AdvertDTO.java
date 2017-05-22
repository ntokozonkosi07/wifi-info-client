package za.co.wifi.info.remote.client.model;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("refNo")
    private String refNo;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("adType")
    private Integer adType;

    @Valid
    @JsonProperty("advertData")
    private AdvertDataDTO advertData;

    @Valid
    @JsonProperty("advertLinkData")
    private AdvertDataDTO advertLinkData;

    @Valid
    @JsonProperty("advertCategories")

    private List<CategoryDTO> advertCategories;

    /**
     * No args constructor for use in serialization
     */
    public AdvertDTO() {
    }

    /**
     * @param advertCategories
     * @param startDate
     * @param advertData
     * @param advertLinkData
     * @param refNo
     * @param endDate
     * @param adType
     */
    public AdvertDTO(String refNo, String startDate, String endDate, Integer adType, AdvertDataDTO advertData, AdvertDataDTO advertLinkData, List<CategoryDTO> advertCategories) {
        super();
        this.refNo = refNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adType = adType;
        this.advertData = advertData;
        this.advertLinkData = advertLinkData;
        this.advertCategories = advertCategories;
    }

    @JsonProperty("refNo")
    public String getRefNo() {
        return refNo;
    }

    @JsonProperty("refNo")
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("adType")
    public Integer getAdType() {
        return adType;
    }

    @JsonProperty("adType")
    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    @JsonProperty("advertData")
    public AdvertDataDTO getAdvertData() {
        return advertData;
    }

    @JsonProperty("advertData")
    public void setAdvertData(AdvertDataDTO advertData) {
        this.advertData = advertData;
    }

    @JsonProperty("advertLinkData")
    public AdvertDataDTO getAdvertLinkData() {
        return advertLinkData;
    }

    @JsonProperty("advertLinkData")
    public void setAdvertLinkData(AdvertDataDTO advertLinkData) {
        this.advertLinkData = advertLinkData;
    }

    @JsonProperty("advertCategories")
    public List<CategoryDTO> getAdvertCategories() {
        return advertCategories;
    }

    @JsonProperty("advertCategories")
    public void setAdvertCategories(List<CategoryDTO> advertCategories) {
        this.advertCategories = advertCategories;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(refNo)
                .append(startDate)
                .append(endDate)
                .append(adType)
                .append(advertData)
                .append(advertLinkData)
                .append(advertCategories)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdvertDTO) == false) {
            return false;
        }
        AdvertDTO rhs = ((AdvertDTO) other);
        return new EqualsBuilder().append(refNo, rhs.refNo)
                .append(startDate, rhs.startDate)
                .append(endDate, rhs.endDate)
                .append(adType, rhs.adType)
                .append(advertData, rhs.advertData)
                .append(advertLinkData, rhs.advertLinkData)
                .append(advertCategories, rhs.advertCategories)
                .isEquals();
    }
}
