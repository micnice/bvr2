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
import zw.co.micnice.logic.dao.accounts.ReceiptItemDAO;
import zw.co.micnice.logic.dao.repo.ReceiptItemRepository;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;
import zw.co.micnice.logic.domain.accounts.TransactionType;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class ReceiptItemDAOImpl implements ReceiptItemDAO {

    @Autowired
    private ReceiptItemRepository receiptItemRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ReceiptItem save(ReceiptItem t) {
        return receiptItemRepository.save(t);
    }

    public List<ReceiptItem> findAll() {
        return receiptItemRepository.findAll();
    }

    public ReceiptItem get(Long id) {
        return receiptItemRepository.findOne(id);
    }

    public ReceiptItemRepository getReceiptItemRepository() {
        return receiptItemRepository;
    }

    public void setReceiptItemRepository(ReceiptItemRepository receiptItemRepository) {
        this.receiptItemRepository = receiptItemRepository;
    }

    public List<ReceiptItem> getReceiptItems(Product product, Date startDate, Date endDate, TransactionType transactionType) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(ReceiptItem.class);
        if (product != null) {
            criteria.createAlias("debtComponent", "d");
            criteria.add(Restrictions.eq("d.product", product));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateCreated", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateCreated", startDate, new Date()));
        }
        if (transactionType != null) {
            criteria.createAlias("receiptHeader", "r");
            criteria.add(Restrictions.eq("r.transactionType", transactionType));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public List<ReceiptItem> getReceiptItemsDeposit(Product product, Date startDate, Date endDate, TransactionType transactionType) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(ReceiptItem.class);
        if (product != null) {
            criteria.createAlias("debtComponent", "d");
            criteria.add(Restrictions.eq("d.product", product));
        }
        criteria.createAlias("receiptHeader", "r");
        if (transactionType != null) {
            criteria.add(Restrictions.eq("r.transactionType", transactionType));
        }
        Criteria c = criteria.createCriteria("r.paymentDetails");
        if (startDate != null && endDate != null) {
            c.add(Restrictions.between("dateOfDeposit", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            c.add(Restrictions.between("dateOfDeposit", startDate, new Date()));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public List<ReceiptItem> getReceiptItems(Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, TransactionType transactionType, User user) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(ReceiptItem.class);
        if (product != null) {
            Criteria p = criteria.createCriteria("debtComponent");
            p.add(Restrictions.eq("product", product));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateCreated", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateCreated", startDate, new Date()));
        }
        criteria.createAlias("receiptHeader", "r");
        if (transactionType != null) {
            criteria.add(Restrictions.eq("r.transactionType", transactionType));
        }
        if (user != null) {
            criteria.add(Restrictions.eq("r.createdBy", user));
        }
        if (paymentMethod != null && paymentType != null) {
            Criteria r = criteria.createCriteria("r.paymentDetails");
            if (paymentMethod != null) {
                r.add(Restrictions.eq("paymentMethod", paymentMethod));
            }
            if (paymentType != null) {
                r.add(Restrictions.eq("paymentType", paymentType));
            }
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public ReceiptItem get(DebtComponent debtComponent) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(ReceiptItem.class);
        criteria.add(Restrictions.eq("debtComponent", debtComponent));
        return (ReceiptItem) criteria.uniqueResult();
    }

    public List<ReceiptItem> getReceiptItemsWithNoReceiptHeader(Account account) {

        return entityManager.createQuery("select rI from ReceiptItem rI where rI.debtComponent.account=:account and rI.receiptHeader IS NULL").setParameter("account", account).getResultList();

    }

    public List<ReceiptItem> getIncomeGenerated(Date startDate, Date endDate) {
        return entityManager.createQuery("SELECT  r from ReceiptItem r  where  r.dateCreated BETWEEN :dateCreated AND :dateCreated").setParameter("dateCreated", startDate).setParameter("dateCreated", endDate).getResultList();

    }
}
