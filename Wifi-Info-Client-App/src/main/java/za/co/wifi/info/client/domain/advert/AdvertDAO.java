package za.co.wifi.info.client.domain.advert;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import za.co.wifi.info.client.domain.DBOperationFailedException;
import za.co.wifi.info.client.domain.category.CategoryEntity;
import za.co.wifi.info.util.Validator;

public class AdvertDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public AdvertDAO(EntityManager em) {
        this.em = em;
    }

    public AdvertEntity save(AdvertEntity advert) throws DBOperationFailedException {
        try {
            em.persist(advert);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
        return advert;
    }

    public AdvertEntity update(AdvertEntity advert) throws DBOperationFailedException {
        try {
            return em.merge(advert);
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }

    public AdvertEntity find(AdvertEntity advert) {
        try {
            Query retval = buildSearch(advert);
            return (AdvertEntity) retval.getSingleResult();
        } catch (DBOperationFailedException | NoResultException ex) {
            return null;
        }
    }

    public List<AdvertEntity> findAll(AdvertEntity advert) throws DBOperationFailedException {
        try {
            Query retval = buildSearch(advert);
            return retval.getResultList();
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }

    public List<AdvertEntity> findAll(List<AdvertType> advertTypes) throws DBOperationFailedException {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT u FROM za.co.wifi.info.client.domain.advert.AdvertEntity u ");
            buff.append("WHERE u.adType IN (:advertTypes) ");
            buff.append("AND :currentDate BETWEEN u.startDate AND u.endDate ");
            buff.append("ORDER BY u.advertRef ASC");

            Query retval = em.createQuery(buff.toString());
            retval.setParameter("advertTypes", advertTypes);
            retval.setParameter("currentDate", new Date());

            return retval.getResultList();
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }

    public List<AdvertEntity> findAll(AdvertType advertType) throws DBOperationFailedException {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT u FROM za.co.wifi.info.client.domain.advert.AdvertEntity u ");
            buff.append("WHERE u.adType =:advertType ");
            buff.append("AND :currentDate BETWEEN u.startDate AND u.endDate ");
            buff.append("ORDER BY u.advertRef ASC");

            Query retval = em.createQuery(buff.toString());
            retval.setParameter("advertType", advertType);
            retval.setParameter("currentDate", new Date());

            return retval.getResultList();
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }
    
    public List<AdvertEntity> findAll(CategoryEntity category, AdvertType advertType) throws DBOperationFailedException {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT u FROM za.co.wifi.info.client.domain.advert.AdvertEntity u,"
                    + "za.co.wifi.info.client.domain.advert.AdvertCategory c ");
            buff.append("WHERE u.adType =:advertType ");
            buff.append("AND u.advertRef = c.advertRef.advertRef ");
            buff.append("AND c.categoryRef.categoryRef =:categoryRef ");
            buff.append("AND :currentDate BETWEEN u.startDate AND u.endDate ");
            buff.append("ORDER BY u.advertRef ASC");

            Query retval = em.createQuery(buff.toString());
            retval.setParameter("advertType", advertType);
            retval.setParameter("categoryRef", category.getCategoryRef());
            retval.setParameter("currentDate", new Date());

            return retval.getResultList();
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }
    
    public List<AdvertEntity> findAll(CategoryEntity category, List<AdvertType> advertTypes) throws DBOperationFailedException {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT u FROM za.co.wifi.info.client.domain.advert.AdvertEntity u, za.co.wifi.info.client.domain.advert.AdvertCategory c ");
            buff.append("WHERE u.advertRef = c.advertRef.advertRef ");
            buff.append("AND c.categoryRef.categoryRef =:categoryRef ");
            buff.append("AND u.adType IN (:advertTypes) ");
            buff.append("AND :currentDate BETWEEN u.startDate AND u.endDate ");
            buff.append("ORDER BY u.advertRef ASC");

            Query retval = em.createQuery(buff.toString());
            retval.setParameter("categoryRef", category.getCategoryRef());
            retval.setParameter("advertTypes", advertTypes);
            retval.setParameter("currentDate", new Date());

            return retval.getResultList();
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }

    public DownloadPageEntity findDownloadPage() {
        try {
            StringBuilder buff = new StringBuilder();
            buff.append("SELECT d FROM za.co.wifi.info.client.domain.advert.DownloadPageEntity d ");
            buff.append("WHERE d.downloadPageRef = 1 ");

            Query retval = em.createQuery(buff.toString());

            return (DownloadPageEntity) retval.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("unused")
    private boolean assessAppend(StringBuilder buff, boolean first) {
        if (first) {
            buff.append(" WHERE ");
            return false;
        } else {
            buff.append(" AND ");
            return first;
        }
    }

    @SuppressWarnings("unused")
    private Query buildSearch(AdvertEntity advert) throws DBOperationFailedException {
        try {
            Query retval = null;
            StringBuilder buff = new StringBuilder();

            buff.append("SELECT u FROM za.co.wifi.info.client.domain.advert.AdvertEntity u");

            boolean first = true;
            int index = 1;

            if (!Validator.getInstance().isEmpty(advert.getAdvertRef())) {
                first = this.assessAppend(buff, first);

                buff.append("u.advertRef =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getAdvertRefNo())) {
                first = this.assessAppend(buff, first);

                buff.append("u.advertRefNo =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getAdType())) {
                first = this.assessAppend(buff, first);

                buff.append("u.adType =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getStartDate())) {
                first = this.assessAppend(buff, first);

                buff.append("u.startDate >=?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getEndDate())) {
                first = this.assessAppend(buff, first);

                buff.append("u.endDate >=?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getCreatedBy())) {
                first = this.assessAppend(buff, first);

                buff.append("u.createdBy =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getCreatedDate())) {
                first = this.assessAppend(buff, first);

                buff.append("u.createdDate >=?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getLastModifiedBy())) {
                first = this.assessAppend(buff, first);

                buff.append("u.lastModifiedBy =?");
                buff.append(index++);
            }
            if (!Validator.getInstance().isEmpty(advert.getLastModifiedDate())) {
                first = this.assessAppend(buff, first);

                buff.append("u.lastModifiedDate <=?");
                buff.append(index++);
            }

            buff.append(" ORDER BY u.advertRef ASC");
            retval = em.createQuery(buff.toString());

            index = 1;

            if (!Validator.getInstance().isEmpty(advert.getAdvertRef())) {
                retval.setParameter(index++, advert.getAdvertRef());
            }
            if (!Validator.getInstance().isEmpty(advert.getAdvertRefNo())) {
                retval.setParameter(index++, advert.getAdvertRefNo());
            }
            if (!Validator.getInstance().isEmpty(advert.getAdType())) {
                retval.setParameter(index++, advert.getAdType());
            }
            if (!Validator.getInstance().isEmpty(advert.getStartDate())) {
                retval.setParameter(index++, advert.getStartDate());
            }
            if (!Validator.getInstance().isEmpty(advert.getEndDate())) {
                retval.setParameter(index++, advert.getEndDate());
            }
            if (!Validator.getInstance().isEmpty(advert.getCreatedBy())) {
                retval.setParameter(index++, advert.getCreatedBy());
            }
            if (!Validator.getInstance().isEmpty(advert.getCreatedDate())) {
                retval.setParameter(index++, advert.getCreatedDate());
            }
            if (!Validator.getInstance().isEmpty(advert.getLastModifiedBy())) {
                retval.setParameter(index++, advert.getLastModifiedBy());
            }
            if (!Validator.getInstance().isEmpty(advert.getLastModifiedDate())) {
                retval.setParameter(index++, advert.getLastModifiedDate());
            }
            return retval;
        } catch (Exception ex) {
            throw new DBOperationFailedException(ex.getLocalizedMessage(), ex);
        }
    }
}
