package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.District;

/**
 *
 * @author Constance Mabaso
 */
public interface DistrictRepository extends CrudRepository<District, Long> {

    public List<District> findAll();
    
}
