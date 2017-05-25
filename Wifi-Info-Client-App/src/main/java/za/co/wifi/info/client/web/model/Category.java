package za.co.wifi.info.client.web.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryRef;

    private String categoryName;

    private Set<Advert> adverts;

    public Category() {
    }

    public Long getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(Long categoryRef) {
        this.categoryRef = categoryRef;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.categoryRef);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.categoryRef, other.categoryRef)) {
            return false;
        }
        return true;
    }
}
