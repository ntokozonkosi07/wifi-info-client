package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import za.co.wifi.info.client.domain.BaseBinaryDataEntity;

@Entity(name = "l_advert_data")
public class AdvertDataEntity extends BaseBinaryDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "advert_data_ref")
    @GeneratedValue(generator = "advert_data", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "advert_data", sequenceName = "advert_data_seq", allocationSize = 1, initialValue = 1)
    private Long advertDataRef;

    public Long getAdvertDataRef() {
        return advertDataRef;
    }

    public void setAdvertDataRef(Long advertDataRef) {
        this.advertDataRef = advertDataRef;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.advertDataRef);
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
        final AdvertDataEntity other = (AdvertDataEntity) obj;
        if (!Objects.equals(this.advertDataRef, other.advertDataRef)) {
            return false;
        }
        return true;
    }
}
