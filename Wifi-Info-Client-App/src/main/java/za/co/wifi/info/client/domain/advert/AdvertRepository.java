package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wifi.info.client.domain.DBOperationFailedException;
import za.co.wifi.info.client.domain.category.CategoryEntity;

@Repository
@Transactional
public class AdvertRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public AdvertEntity save(AdvertEntity advert) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.save(advert);
    }
    
    public DownloadPageEntity save(DownloadPageEntity downloadPage) throws DBOperationFailedException {
        try {
            return em.merge(downloadPage);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }

    public AdvertEntity update(AdvertEntity advert) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.update(advert);
    }

    public AdvertEntity find(AdvertEntity advert) {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.find(advert);
    }

    public List<AdvertEntity> findAll(AdvertEntity advert) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(advert);
    }

    public List<AdvertEntity> findAll(AdvertType advertType) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(advertType);
    }
    
    public List<AdvertEntity> findAll(List<AdvertType> advertTypes) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(advertTypes);
    }

    public List<AdvertEntity> findAll(CategoryEntity category, AdvertType advertType) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(category, advertType);
    }
    
    public List<AdvertEntity> findAll(CategoryEntity category, List<AdvertType> advertTypes) throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findAll(category, advertTypes);
    }

    public DownloadPageEntity findDownloadPage() throws DBOperationFailedException {
        AdvertDAO advertDAO = new AdvertDAO(em);
        return advertDAO.findDownloadPage();
    }
}
