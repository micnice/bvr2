
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Province;

/**
 *
 * @author Kelvin Goredema
 */
public interface ProvinceRepository extends CrudRepository<Province, Long>{
    
        public List<Province> findAll();

    
    
    
}
