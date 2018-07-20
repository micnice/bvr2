package zw.co.micnice.logic.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.RegistrantDAO;
import zw.co.micnice.logic.dao.repo.RegistrantRepository;
import zw.co.micnice.logic.domain.Citizenship;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.AccountType;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.service.GeneralParametersService;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class RegistrantDAOImpl implements RegistrantDAO {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private RegistrantRepository registrantRepository;
    @Autowired
    private GeneralParametersService generalParametersService;
    @PersistenceContext
    EntityManager entityManager;

    /**
     *
     * @param query
     * @return
     */
    public List<Registrant> getRegistrants(String query, String gender, Citizenship citizenship,Boolean dead) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        if (query != null) {
            criteria.createAlias("customerAccount", "c");
            criteria.add(Restrictions.or(Restrictions.like("firstname", query, MatchMode.START), Restrictions.or(Restrictions.like("lastname", query, MatchMode.START), Restrictions.or(Restrictions.like("middlename", query,
                    MatchMode.START), Restrictions.eq("idNumber", query)), Restrictions.or(Restrictions.like("c.code", query), Restrictions.like("registrationNumber", query)))));
            criteria.setMaxResults(60);
        }
        if (gender != null) {
            criteria.add(Restrictions.eq("gender", gender));
        }
        if (citizenship != null) {
            criteria.add(Restrictions.eq("citizenship", citizenship));
        }
        if (dead != null) {
            criteria.add(Restrictions.eq("dead", dead));
        }
        criteria.addOrder(Order.asc("lastname"));
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public List<Registrant> getRegistrantsByCitizenship(Citizenship citizenship) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("citizenship", citizenship));
        criteria.addOrder(Order.asc("lastname"));
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public List<Registrant> getRegistrantsByGender(String gender) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("gender", gender));
        criteria.addOrder(Order.asc("lastname"));
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public Registrant getRegistrant(Customer customer) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("customerAccount", customer));
        return (Registrant) criteria.uniqueResult();
    }
    
    public Registrant createUids(Registrant registrant){
        
            if (registrant.getUid() == null){
             registrant.setUid(UUID.randomUUID().toString());
             
            }
            return registrant;
        
    }
    
    public List<Registrant> getApprovingRegistrants(){
        Boolean isAnApprover = Boolean.TRUE;
        return entityManager.createQuery("select r from Registrant r where r.isAnApprover=:isAnApprover").setParameter("isAnApprover", isAnApprover).getResultList();
    }

    public Registrant save(Registrant registrant) {
        if (registrant.getCustomerAccount() == null || registrant.getCustomerAccount().getId() == null) {
            registrant.setCustomerAccount(new Customer());
            registrant.setUid(UUID.randomUUID().toString());
            registrant.getCustomerAccount().setCode(UUID.randomUUID().toString());
            registrant.getCustomerAccount().setAccount(new Account());
            registrant.getCustomerAccount().getAccount().setBalance(BigDecimal.ZERO);
            registrant.getCustomerAccount().getAccount().setAccountType(AccountType.ACCOUNTS_RECEIVABLE);
            registrant.getCustomerAccount().getAccount().setCode(UUID.randomUUID().toString());
            registrant.getCustomerAccount().setCustomerName(registrant.getFullname());
            registrant.getCustomerAccount().getAccount().setName(registrant.getFullname());
            registrant.getCustomerAccount().getAccount().setPersonalAccount(Boolean.TRUE);
        }
        return registrantRepository.save(registrant);
    }

    public List<Registrant> findAll() {
        return registrantRepository.findAll();
    }

    public Registrant get(Long id) {
        return registrantRepository.findOne(id);
    }

    public void setRegistrantRepository(RegistrantRepository registrantRepository) {
        this.registrantRepository = registrantRepository;
    }

    
    public Registrant getRegistrantByAccount(Customer customer) {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("customerAccount", customer));
        return (Registrant) criteria.uniqueResult();
    }

    public Boolean getRegistrantIdentificationNumber(String idNumber) {
        if (getRegistrantByIdentificationNumber(idNumber) == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean getUniqueRegistrationNumber(String registrationNumber) {
        if (getRegistrantByRegistrationNumber(registrationNumber) == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Registrant getRegistrantByRegistrationNumber(String registrationNumber) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("registrationNumber", registrationNumber));
        return (Registrant) criteria.uniqueResult();

    }

    public Registrant getRegistrantByIdentificationNumber(String idNumber) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Registrant.class);
        criteria.add(Restrictions.eq("idNumber", idNumber));
        return (Registrant) criteria.uniqueResult();
    }
    
    public List<Long> getIds(){
        return entityManager.createQuery("select r.id from Registrant r").getResultList();
    }

    public List<Registrant> getDeRegistrants() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Registrant> getRegistrantAgeByParameter(Integer lowerLimit, Integer upperLimit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
