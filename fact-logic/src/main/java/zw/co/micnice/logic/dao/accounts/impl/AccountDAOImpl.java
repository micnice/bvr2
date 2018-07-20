
package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.AccountDAO;
import zw.co.micnice.logic.dao.repo.AccountRepository;
import zw.co.micnice.logic.domain.accounts.Account;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
@Repository
public class AccountDAOImpl implements AccountDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account t) {
        return accountRepository.save(t);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account get(Long id) {
        return accountRepository.findOne(id);
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public List<Account> getGLAccounts(){
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Expression.eq("personalAccount", Boolean.FALSE));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
