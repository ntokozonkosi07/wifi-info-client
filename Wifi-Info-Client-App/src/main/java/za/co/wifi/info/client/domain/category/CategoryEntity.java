package za.co.wifi.info.client.domain.category;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import za.co.wifi.info.client.domain.BaseEntity;

@Entity(name = "l_category")
public class CategoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_ref")
    private Long categoryRef;

    @Column(name = "category_name", length = 80, nullable = false)
    private String categoryName;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.categoryRef);
        hash = 23 * hash + Objects.hashCode(this.categoryName);
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
        final CategoryEntity other = (CategoryEntity) obj;
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        if (!Objects.equals(this.categoryRef, other.categoryRef)) {
            return false;
        }
        return true;
    }
}
