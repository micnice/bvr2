/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.repo;

import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.City;


public interface CityRepository extends CrudRepository<City, Long> {
    
}
