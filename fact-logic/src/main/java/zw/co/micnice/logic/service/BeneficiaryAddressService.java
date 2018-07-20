/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.BeneficiaryAddress;

/**
 *
 * @author kelvin
 */
public interface BeneficiaryAddressService extends IGenericService<BeneficiaryAddress> {
    public List<BeneficiaryAddress> findByBeneficiary(Beneficiary beneficiary);
}
