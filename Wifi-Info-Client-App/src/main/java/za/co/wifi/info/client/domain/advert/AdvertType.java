package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum AdvertType implements Serializable {

    STANDARD(1, "Standard Advert"),
    HALF_PAGE(2, "Half Page Advert"),
    FULL_PAGE(3, "Full Page Advert"),
    LINK(4, "Banner Advert"),
    BANNER(5, "Banner Broadcaster");

    private final int id;
    private final String description;

    private AdvertType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static AdvertType getAdvertType(int id) {
        List<AdvertType> advertTypeEnums = Arrays.asList(values());
        for (AdvertType advertTypeEnum : advertTypeEnums) {
            if (advertTypeEnum.getId() == id) {
                return advertTypeEnum;
            }
        }
        return null;
    }

    public static List<AdvertType> getAllAdvertTypes() {
        return Arrays.asList(values());
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
