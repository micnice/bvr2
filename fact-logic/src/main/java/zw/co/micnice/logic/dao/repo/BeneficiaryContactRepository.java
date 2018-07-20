/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryContact;


public interface BeneficiaryContactRepository extends CrudRepository<BeneficiaryContact, Long>{
     public List<BeneficiaryContact> findAll();
     public List<BeneficiaryContact> findByBeneficiary(Beneficiary beneficiary);
}
