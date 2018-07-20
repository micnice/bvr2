/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.dao.repo.BeneficiaryRepository;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.AccountType;
import zw.co.micnice.logic.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Beneficiary save(Beneficiary beneficiary) {

        return beneficiaryRepository.save(beneficiary);
    }

    @Transactional(readOnly = true)
    public List<Beneficiary> findAll() {
//        return (List<Beneficiary>) beneficiaryRepository.findAll();
        return entityManager.createQuery("select c from Beneficiary c ORDER BY c.beneficiaryName,c.numberOfEmployees").getResultList();
    }

    @Transactional(readOnly = true)
    public Beneficiary get(Long id) {
        return beneficiaryRepository.findOne(id);
    }
    
    @Transactional(readOnly = true)
    public List<Beneficiary> getCompanies (String beneficiaryName){
        
        //TODO : Include the maximum query result as part of the general parameters        
        return entityManager.createQuery("SELECT c from Beneficiary c WHERE c.firstName LIKE :firstName "
                + "ORDER BY c.firstName")
                            .setParameter("firstName", "%" + beneficiaryName + "%")
                            .setMaxResults(60)
                            .getResultList();
    }

    public List<Beneficiary> getCompanies() {
       return entityManager.createQuery("select c from Beneficiary c where c.sold=FALSE").getResultList();
    }

    public List<Beneficiary> getClosedItems() {
        return entityManager.createQuery("select c from Beneficiary c where c.sold=TRUE").getResultList();
    }
    
}
