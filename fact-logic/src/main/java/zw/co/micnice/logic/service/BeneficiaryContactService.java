/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryContact;

/**
 *
 * @author kelvin
 */
public interface BeneficiaryContactService extends IGenericService<BeneficiaryContact> {
    public List<BeneficiaryContact> findByBeneficiary(Beneficiary beneficiary);
}
