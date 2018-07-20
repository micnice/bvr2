package zw.co.micnice.logic.dao.accounts.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.ReceiptHeaderDAO;
import zw.co.micnice.logic.dao.repo.ReceiptHeaderRepository;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;
import zw.co.micnice.logic.domain.accounts.TransactionType;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class ReceiptHeaderDAOImpl implements ReceiptHeaderDAO {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ReceiptHeaderRepository receiptHeaderRepository;

    public ReceiptHeader save(ReceiptHeader t) {
        return receiptHeaderRepository.save(t);
    }

    public List<ReceiptHeader> findAll() {
        return receiptHeaderRepository.findAll();
    }

    public ReceiptHeader get(Long id) {
        return receiptHeaderRepository.findOne(id);
    }

    public ReceiptHeaderRepository getReceiptHeaderRepository() {
        return receiptHeaderRepository;
    }

    public void setReceiptHeaderRepository(ReceiptHeaderRepository receiptHeaderRepository) {
        this.receiptHeaderRepository = receiptHeaderRepository;
    }

    public List<ReceiptHeader> getReceiptHeaders(Long receiptNumber, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, TransactionType transactionType, Boolean reversed) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(ReceiptHeader.class);
        if (receiptNumber != null) {
            criteria.add(Restrictions.or(Restrictions.eq("id", receiptNumber), Restrictions.eq("generatedReceiptNumber", receiptNumber)));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("date", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("date", startDate, new Date()));
        }
        if (user != null) {
            criteria.add(Restrictions.eq("createdBy", user));
        }
        if (transactionType != null) {
            criteria.add(Restrictions.eq("transactionType", transactionType));
        }
        if (reversed != null) {
            criteria.add(Restrictions.eq("reversed", reversed));
        }
        criteria.createAlias("paymentDetails", "r");
        if (paymentMethod != null) {
            criteria.add(Restrictions.eq("r.paymentMethod", paymentMethod));
        }
        if (paymentType != null) {
            criteria.add(Restrictions.eq("r.paymentType", paymentType));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    public List<ReceiptHeader> getReceiptHeadersDeposit(Long receiptNumber, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, TransactionType transactionType, Boolean reversed) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(ReceiptHeader.class);
        if (receiptNumber != null) {
            criteria.add(Restrictions.or(Restrictions.eq("id", receiptNumber), Restrictions.eq("generatedReceiptNumber", receiptNumber)));
        }
        if (user != null) {
            criteria.add(Restrictions.eq("createdBy", user));
        }
        if (transactionType != null) {
            criteria.add(Restrictions.eq("transactionType", transactionType));
        }
        if (reversed != null) {
            criteria.add(Restrictions.eq("reversed", reversed));
        }
        criteria.createAlias("paymentDetails", "r");
        if (paymentMethod != null) {
            criteria.add(Restrictions.eq("r.paymentMethod", paymentMethod));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("r.dateOfDeposit", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("r.dateOfDeposit", startDate, new Date()));
        }
        if (paymentType != null) {
            criteria.add(Restrictions.eq("r.paymentType", paymentType));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    public List<ReceiptHeader> getReceiptNumbers(ReceiptHeader receiptHeader) {
        return em.createQuery("select generatedReceiptNumber from ReceiptHeader").getResultList();

    }
}
