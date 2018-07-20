package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.CustomerDAO;
import zw.co.micnice.logic.dao.repo.CustomerRepository;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;

/**
 *
 * @author tdhlakama
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private CustomerRepository customerAccountRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Customer save(Customer customerAccount) {
        return customerAccountRepository.save(customerAccount);
    }

    public List<Customer> findAll() {
        return customerAccountRepository.findAll();
    }

    public Customer get(Long id) {
        return customerAccountRepository.findOne(id);
    }

    public void setCustomerAccountRepository(CustomerRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public Customer get(Account account) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("account", account));
        return (Customer) criteria.uniqueResult();
    }

    public Registrant getRegistrant(Account account) {
        return getRegistrant(get(account));
    }
    
    public Registrant getRegistrant(Customer customer) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("customerAccount", customer));
        return (Registrant) criteria.uniqueResult();
    }
}