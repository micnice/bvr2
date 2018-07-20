/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.BeneficiaryAddressDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryAddress;
import zw.co.micnice.logic.service.BeneficiaryAddressService;


@Service
@Transactional(readOnly = true)
public class BeneficiaryAddressServiceImpl implements BeneficiaryAddressService {

    @Autowired
    private BeneficiaryAddressDAO beneficiaryAddressDAO;

    @Transactional
    public BeneficiaryAddress save(BeneficiaryAddress beneficiaryAddress) {
        return beneficiaryAddressDAO.save(beneficiaryAddress);
    }

    public List<BeneficiaryAddress> findAll() {
        return beneficiaryAddressDAO.findAll();
    }

    public BeneficiaryAddress get(Long id) {
        return beneficiaryAddressDAO.get(id);
    }

    public void setRegistrantAddressDAO(BeneficiaryAddressDAO beneficiaryAddressDAO) {
        this.beneficiaryAddressDAO = beneficiaryAddressDAO;
    }

    public List<BeneficiaryAddress> findByBeneficiary(Beneficiary beneficiary) {
        return beneficiaryAddressDAO.findByBeneficiary(beneficiary);
    }
    
    
}
