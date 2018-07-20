package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.TransactionTypeDAO;
import zw.co.micnice.logic.dao.repo.TransactionTypeRepository;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.accounts.Effect;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.TransactionType;


/**
 *
 * @author Charles Chigoriwa
 * @author Michael Matiashe
 */
@Repository
public class TransactionTypeDAOImpl implements TransactionTypeDAO {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public TransactionType save(TransactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    public List<TransactionType> findAll() {
        return transactionTypeRepository.findAll();
    }

    public TransactionType get(Long id) {
        return transactionTypeRepository.findOne(id);
    }

    public List<TransactionType> find(Book book) {
        return entityManager
                .createQuery("select t from TransactionType t where t.book=:book")
                .setParameter("book", book).getResultList();
    }
    

    public List<TransactionType> findBankAccounts() {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(TransactionType.class);
        criteria.add(org.hibernate.criterion.Restrictions.eq("effect", Effect.CR));
        criteria.add(org.hibernate.criterion.Restrictions.eq("book", Book.CB));
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
    

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param transactionTypeRepository
     */
    public void setTransactionTypeRepository(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public List<TransactionType> find(Book book, PaymentMethod paymentMethod) {
        return entityManager.createQuery("select t from TransactionType t where t.book=:book and t.paymentMethod=:paymentMethod").setParameter("book", book).setParameter("paymentMethod", paymentMethod).getResultList();
    }
}
