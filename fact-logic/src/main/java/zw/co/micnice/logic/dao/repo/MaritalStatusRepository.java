package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.MaritalStatus;

/**
 *
 * @author Edward Zengeni
 */

    public interface MaritalStatusRepository extends CrudRepository<MaritalStatus, Long> {
    public List<MaritalStatus> findAll();
}


