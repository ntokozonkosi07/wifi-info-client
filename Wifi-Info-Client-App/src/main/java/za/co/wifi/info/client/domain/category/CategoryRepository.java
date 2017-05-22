package za.co.wifi.info.client.domain.category;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.client.domain.DBOperationFailedException;

@Repository
@Transactional
public class CategoryRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public CategoryEntity save(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.save(categoryDTO);
    }

    public CategoryEntity update(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.update(categoryDTO);
    }

    public CategoryEntity find(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.find(categoryDTO);
    }

    public List<CategoryEntity> findAll(CategoryEntity categoryDTO) throws DBOperationFailedException {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        return categoryDAO.findAll(categoryDTO);
    }

    public List<CategoryEntity> findAllAdvertCategories() throws DBOperationFailedException {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT DISTINCT c FROM za.co.wifi.info.client.domain.category.CategoryEntity c, "
                    + "za.co.wifi.info.client.domain.advert.AdvertEntity a, "
                    + "za.co.wifi.info.client.domain.advert.AdvertCategoryEntity ac ");
            buff.append("WHERE a.advertRef = ac.advertRef.advertRef ");
            buff.append("AND ac.categoryRef.categoryRef = c.categoryRef ");
            buff.append("ORDER BY c.categoryName ASC");

            Query retval = em.createQuery(buff.toString());

            return retval.getResultList();
        } catch (Exception ex) {
            //Do nothing
        }

        return Arrays.asList();
    }
}
