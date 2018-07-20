package zw.co.micnice.logic.dao.accounts.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.OldReceiptDAO;
import zw.co.micnice.logic.dao.repo.OldReceiptRepository;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.OldReceipt;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.service.GeneralParametersService;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class OldReceiptDAOImpl implements OldReceiptDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private OldReceiptRepository oldReceiptRepository;
    @Autowired
    private GeneralParametersService generalParametersService;

    public OldReceipt save(OldReceipt t) {
        return oldReceiptRepository.save(t);
    }

    public List<OldReceipt> findAll() {
        return oldReceiptRepository.findAll();
    }

    public OldReceipt get(Long id) {
        return oldReceiptRepository.findOne(id);
    }

    public OldReceiptRepository getOldReceiptRepository() {
        return oldReceiptRepository;
    }

    public void setOldReceiptRepository(OldReceiptRepository oldReceiptRepository) {
        this.oldReceiptRepository = oldReceiptRepository;
    }

    public List<OldReceipt> getReceipts(Long receiptNumber, Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, Boolean cancel, Boolean bank) {

        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(OldReceipt.class);
        if (product != null) {
            criteria.add(Restrictions.eq("product", product));
        }
        if (receiptNumber != null) {
            criteria.add(Restrictions.eq("receiptRecordID", receiptNumber));
        }
        if (paymentMethod != null) {
            criteria.add(Restrictions.eq("paymentMethod", paymentMethod));
        }
        if (paymentType != null) {
            criteria.add(Restrictions.eq("paymentType", paymentType));
        }
        if (bank != null) {
            criteria.add(Restrictions.eq("bank", bank));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, new Date()));
        }
        if (user != null) {
            criteria.add(Restrictions.eq("user", user));
        }
        if (cancel != null) {
            criteria.add(Restrictions.eq("cancelled", cancel));
        }
        criteria.addOrder(Order.desc("dateOfReceipt"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    public List<OldReceipt> getReceipts(Product product, Date startDate, Date endDate, Boolean cancel) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(OldReceipt.class);
        if (product != null) {
            criteria.add(Restrictions.eq("product", product));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, new Date()));
        }
        if (cancel != null) {
            criteria.add(Restrictions.eq("cancelled", cancel));
        }
        criteria.addOrder(Order.desc("dateOfReceipt"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public List<OldReceipt> getReceipts(Product product, Registrant registrant) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(OldReceipt.class);
        if (product != null) {
            criteria.add(Restrictions.eq("product", product));
        }
        if (registrant != null) {
            criteria.add(Restrictions.eq("registrant", registrant));
        }
        criteria.addOrder(Order.desc("dateOfReceipt"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public List<OldReceipt> getOldRenewalReceipts() {
        return entityManager.createQuery("SELECT r FROM OldReceipt r WHERE (r.paymentPurpose=:paymentPurpose or r.paymentPurpose=:paymentPurpose1 or r.paymentPurpose=:paymentPurpose2) and r.dateOfReceipt>=:date").setParameter("paymentPurpose", "Annual Fees Payment").setParameter("paymentPurpose1", "Re-Registration Fees").setParameter("paymentPurpose2", "Registration Fee Payment").setParameter("date", new Date(12, 12, 1)).getResultList();
    }

    public Double getReceiptTotal(Long receiptNumber, Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, Boolean cancel, Boolean bank) {

        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(OldReceipt.class);
        if (product != null) {
            criteria.add(Restrictions.eq("product", product));
        }
        if (receiptNumber != null) {
            criteria.add(Restrictions.eq("receiptRecordID", receiptNumber));
        }
        if (paymentMethod != null) {
            criteria.add(Restrictions.eq("paymentMethod", paymentMethod));
        }
        if (paymentType != null) {
            criteria.add(Restrictions.eq("paymentType", paymentType));
        }
        if (bank != null) {
            criteria.add(Restrictions.eq("bank", bank));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, new Date()));
        }
        if (user != null) {
            criteria.add(Restrictions.eq("user", user));
        }
        if (cancel != null) {
            criteria.add(Restrictions.eq("cancelled", cancel));
        }
        criteria.setProjection(Projections.sum("lineTotal"));
        Double totalDurationDouble = (Double) criteria.uniqueResult();
        System.out.println("---------------------------------Data-----------------------" + totalDurationDouble);
        return ((Double) criteria.uniqueResult()).doubleValue();
    }

    public Double getReceiptTotal(Product product, Date startDate, Date endDate, Boolean cancel) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(OldReceipt.class);
        if (product != null) {
            criteria.add(Restrictions.eq("product", product));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateOfReceipt", startDate, new Date()));
        }
        if (cancel != null) {
            criteria.add(Restrictions.eq("cancelled", cancel));
        }
        criteria.setProjection(Projections.sum("lineTotal"));
        return ((Double) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult()).doubleValue();
    }
}
