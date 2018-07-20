package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.PaymentDetailsDAO;
import zw.co.micnice.logic.dao.repo.PaymentDetailsRepository;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
@Repository
public class PaymentDetailsDAOImpl implements PaymentDetailsDAO {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    public PaymentDetails save(PaymentDetails t) {
        return paymentDetailsRepository.save(t);
    }

    public List<PaymentDetails> findAll() {
        return paymentDetailsRepository.findAll();
    }

    public PaymentDetails get(Long id) {
        return paymentDetailsRepository.findOne(id);
    }

    public PaymentDetailsRepository getPaymentDetailsRepository() {
        return paymentDetailsRepository;
    }

    public void setPaymentDetailsRepository(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    public List<PaymentDetails> transactionHistory(Customer customer) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(PaymentDetails.class);
        criteria.add(org.hibernate.criterion.Restrictions.eq("customer", customer));
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
