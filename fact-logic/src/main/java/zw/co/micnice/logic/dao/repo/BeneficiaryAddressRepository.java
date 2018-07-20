/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryAddress;


public interface BeneficiaryAddressRepository extends CrudRepository<BeneficiaryAddress, Long>{
     public List<BeneficiaryAddress> findAll();
     public List<BeneficiaryAddress> findByBeneficiary(Beneficiary beneficiary);
}
