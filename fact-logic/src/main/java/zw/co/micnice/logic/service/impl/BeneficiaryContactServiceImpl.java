/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.BeneficiaryContactDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryContact;
import zw.co.micnice.logic.service.BeneficiaryContactService;


@Service
@Transactional(readOnly = true)
public class BeneficiaryContactServiceImpl implements BeneficiaryContactService {

    @Autowired
    private BeneficiaryContactDAO beneficiaryContactDAO;

    @Transactional
    public BeneficiaryContact save(BeneficiaryContact beneficiaryContact) {
        return beneficiaryContactDAO.save(beneficiaryContact);
    }

    public List<BeneficiaryContact> findAll() {
        return beneficiaryContactDAO.findAll();
    }

    public BeneficiaryContact get(Long id) {
        return beneficiaryContactDAO.get(id);
    }

    public void setRegistrantContactDAO(BeneficiaryContactDAO beneficiaryContactDAO) {
        this.beneficiaryContactDAO = beneficiaryContactDAO;
    }

    public List<BeneficiaryContact> findByBeneficiary(Beneficiary beneficiary) {
        return beneficiaryContactDAO.findByBeneficiary(beneficiary);
    }
    
    
}
