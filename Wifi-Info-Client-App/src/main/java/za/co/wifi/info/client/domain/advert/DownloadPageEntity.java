package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "l_download_page")
public class DownloadPageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "download_page_ref")
    @GeneratedValue(generator = "download_page", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "download_page", sequenceName = "download_page_seq", allocationSize = 1, initialValue = 1)
    private Long downloadPageRef;
    
    @Column(name = "binary_data", nullable = false)
    private byte[] downloadPageData;

    public Long getDownloadPageRef() {
        return downloadPageRef;
    }

    public void setDownloadPageRef(Long downloadPageRef) {
        this.downloadPageRef = downloadPageRef;
    }

    public byte[] getDownloadPageData() {
        return downloadPageData;
    }

    public void setDownloadPageData(byte[] downloadPageData) {
        this.downloadPageData = downloadPageData;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.downloadPageRef);
        hash = 13 * hash + Arrays.hashCode(this.downloadPageData);
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
        final DownloadPageEntity other = (DownloadPageEntity) obj;
        if (!Objects.equals(this.downloadPageRef, other.downloadPageRef)) {
            return false;
        }
        if (!Arrays.equals(this.downloadPageData, other.downloadPageData)) {
            return false;
        }
        return true;
    }
}
