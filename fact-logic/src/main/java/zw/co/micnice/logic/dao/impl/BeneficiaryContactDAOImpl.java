/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.BeneficiaryContactDAO;
import zw.co.micnice.logic.dao.repo.BeneficiaryContactRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryContact;


@Repository
public class BeneficiaryContactDAOImpl implements BeneficiaryContactDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private BeneficiaryContactRepository beneficiaryContactRepository;

    public BeneficiaryContact save(BeneficiaryContact beneficiaryContact) {
        return beneficiaryContactRepository.save(beneficiaryContact);
    }

    public List<BeneficiaryContact> findAll() {
        return beneficiaryContactRepository.findAll();
    }

    public BeneficiaryContact get(Long id) {
        return beneficiaryContactRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param beneficiaryContactRepository
     */
    public void setBeneficiaryContactRepository(BeneficiaryContactRepository beneficiaryContactRepository) {
        this.beneficiaryContactRepository = beneficiaryContactRepository;
    }

    public List<BeneficiaryContact> getContacts(Beneficiary beneficiary) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(BeneficiaryContact.class);
        if (beneficiary == null) {
            return new ArrayList<BeneficiaryContact>();
        } else {

            criteria.add(Restrictions.eq("beneficiary", beneficiary));
            return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
    }

    public List<BeneficiaryContact> findByBeneficiary(Beneficiary beneficiary) {
        return beneficiaryContactRepository.findByBeneficiary(beneficiary);
    }
}
