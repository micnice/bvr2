/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.repo;

import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
    
}
