/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service;

import java.util.List;
import org.hibernate.Query;
import zw.co.micnice.logic.domain.Beneficiary;

public interface BeneficiaryService  extends IGenericService<Beneficiary> {
    public List<Beneficiary> getCompanies (String beneficiaryName);
    public List<Beneficiary> getCompanies();
    public List<Beneficiary> getClosedItems();
}
