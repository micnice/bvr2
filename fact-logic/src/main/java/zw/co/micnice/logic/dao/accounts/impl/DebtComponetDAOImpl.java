package zw.co.micnice.logic.dao.accounts.impl;

import java.util.ArrayList;
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
import zw.co.micnice.logic.dao.accounts.DebtComponentDAO;
import zw.co.micnice.logic.dao.repo.DebtComponentRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.Product;

/**
 * e
 *
 * @author Michael Matiashe
 */
@Repository
public class DebtComponetDAOImpl implements DebtComponentDAO {

    @Autowired
    private DebtComponentRepository debtComponentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public DebtComponent save(DebtComponent t) {
        return debtComponentRepository.save(t);
    }

    public List<DebtComponent> findAll() {
        return debtComponentRepository.findAll();
    }

    public DebtComponent get(Long id) {
        return debtComponentRepository.findOne(id);
    }

    public DebtComponentRepository getDebtComponentRepository() {
        return debtComponentRepository;
    }

    public void setDebtComponentRepository(DebtComponentRepository debtComponentRepository) {
        this.debtComponentRepository = debtComponentRepository;
    }

    public List<DebtComponent> getDebtComponents(Account account) {
        return entityManager.createQuery("select d from DebtComponent d where d.account=:account and d.remainingBalance>0").setParameter("account", account).getResultList();
    }
    
    public List<DebtComponent> getDeptComponents(Document document){
    return entityManager.createQuery("select d from DebtComponent d WHERE d.document=:document").setParameter("document", document).getResultList();
    }

    public List<DebtComponent> getCurrentDebtComponents(Account account) {
        List<DebtComponent> debtComponents = new ArrayList<DebtComponent>();
        for (DebtComponent debtComponent : getDebtComponents(account)) {
            if (debtComponent.getDateCreated().getMonth() == new Date().getMonth() && debtComponent.getDateCreated().getYear() == new Date().getYear()) {
                debtComponents.add(debtComponent);

            }
        }
        return debtComponents;
    }

    public List<DebtComponent> getOneMonthDebtComponents(Account account) {
        List<DebtComponent> debtComponents = new ArrayList<DebtComponent>();
        for (DebtComponent debtComponent : getDebtComponents(account)) {
            if (debtComponent.getDateCreated().getMonth() == new Date().getMonth() - 1 && debtComponent.getDateCreated().getYear() == new Date().getYear()) {
                debtComponents.add(debtComponent);

            }
        }
        return debtComponents;
    }

    public List<DebtComponent> getTwoMonthsDebtComponents(Account account) {
        List<DebtComponent> debtComponents = new ArrayList<DebtComponent>();
        for (DebtComponent debtComponent : getDebtComponents(account)) {
            if (debtComponent.getDateCreated().getMonth() == new Date().getMonth() - 2 && debtComponent.getDateCreated().getYear() == new Date().getYear()) {
                debtComponents.add(debtComponent);

            }
        }
        return debtComponents;
    }

    public List<DebtComponent> getThreeMonthsOrMoreDebtComponents(Account account) {
        List<DebtComponent> debtComponents = new ArrayList<DebtComponent>();
        for (DebtComponent debtComponent : getDebtComponents(account)) {
            if (debtComponent.getDateCreated().getMonth() < new Date().getMonth() - 2 && debtComponent.getDateCreated().getYear() == new Date().getYear()) {
                debtComponents.add(debtComponent);

            }
        }
        return debtComponents;
    }

    public List<DebtComponent> getDebtComponents(Customer customerAccount) {
        return getDebtComponents(customerAccount.getAccount());
    }

    public List<DebtComponent> getDebtComponents(Beneficiary beneficiary) {
        return getDebtComponents(new Account());
    }

    public List<DebtComponent> getDebtComponents(Product product, Date startDate, Date endDate) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(DebtComponent.class);
        if (product != null) {
            criteria.add(Restrictions.eq("product", product));
        }
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("dateCreated", startDate, endDate));
        } else if (startDate != null && endDate == null) {
            criteria.add(Restrictions.between("dateCreated", startDate, new Date()));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public int getTotalDues(Account account, Boolean threeOrMore, Boolean twoMonths, Boolean oneMonth, Boolean current) {
        int total=0;
        List<DebtComponent> debtComponents = new ArrayList<DebtComponent>();
        if (threeOrMore) {
            debtComponents = getThreeMonthsOrMoreDebtComponents(account);
        } else if (twoMonths) {
            debtComponents = getTwoMonthsDebtComponents(account);
        } else if (oneMonth) {
            debtComponents = getOneMonthDebtComponents(account);
        } else if (current) {
            debtComponents = getCurrentDebtComponents(account);
        } else {
            debtComponents = getDebtComponents(account);
        }
        for (DebtComponent debtComponent : debtComponents) {
            total += debtComponent.getRemainingBalance().intValue();
        }
        System.out.println("=========================Total = " + total + "======================");
        return total;
    }
}
