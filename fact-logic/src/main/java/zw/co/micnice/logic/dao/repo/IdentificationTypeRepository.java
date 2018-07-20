package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.IdentificationType;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IdentificationTypeRepository extends CrudRepository<IdentificationType, Long> {

    public List<IdentificationType> findAll();
}
