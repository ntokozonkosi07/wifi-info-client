package za.co.wifi.info.controller.model;

import java.io.Serializable;

import java.util.Objects;

public class BannerLink implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bannerLinkRefNo;

    private File bannerAdvert;

    private File bannerLink;

    public BannerLink() {
    }

    public String getBannerLinkRefNo() {
        return bannerLinkRefNo;
    }

    public void setBannerLinkRefNo(String bannerLinkRefNo) {
        this.bannerLinkRefNo = bannerLinkRefNo;
    }

    public File getBannerAdvert() {
        return bannerAdvert;
    }

    public void setBannerAdvert(File bannerAdvert) {
        this.bannerAdvert = bannerAdvert;
    }

    public File getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(File bannerLink) {
        this.bannerLink = bannerLink;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.bannerLinkRefNo);
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
        final BannerLink other = (BannerLink) obj;
        if (!Objects.equals(this.bannerLinkRefNo, other.bannerLinkRefNo)) {
            return false;
        }
        return true;
    }
}
