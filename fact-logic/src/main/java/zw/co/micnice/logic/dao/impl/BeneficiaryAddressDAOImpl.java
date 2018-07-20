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
import zw.co.micnice.logic.dao.BeneficiaryAddressDAO;
import zw.co.micnice.logic.dao.repo.BeneficiaryAddressRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryAddress;


@Repository
public class BeneficiaryAddressDAOImpl implements BeneficiaryAddressDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private BeneficiaryAddressRepository beneficiaryAddressRepository;

    public BeneficiaryAddress save(BeneficiaryAddress beneficiaryAddress) {
        return beneficiaryAddressRepository.save(beneficiaryAddress);
    }

    public List<BeneficiaryAddress> findAll() {
        return beneficiaryAddressRepository.findAll();
    }

    public BeneficiaryAddress get(Long id) {
        return beneficiaryAddressRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param beneficiaryAddressRepository
     */
    public void setBeneficiaryAddressRepository(BeneficiaryAddressRepository beneficiaryAddressRepository) {
        this.beneficiaryAddressRepository = beneficiaryAddressRepository;
    }

    public List<BeneficiaryAddress> getAddresss(Beneficiary beneficiary) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(BeneficiaryAddress.class);
        if (beneficiary == null) {
            return new ArrayList<BeneficiaryAddress>();
        } else {

            criteria.add(Restrictions.eq("beneficiary", beneficiary));
            return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
    }

    public List<BeneficiaryAddress> findByBeneficiary(Beneficiary beneficiary) {
        return beneficiaryAddressRepository.findByBeneficiary(beneficiary);
    }
}
