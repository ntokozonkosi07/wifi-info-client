package za.co.wifi.info.controller.model;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryRef;

    private String categoryName;

    private List<Advert> adverts;

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

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
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
