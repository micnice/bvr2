
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Citizenship;

/**
 *
 * @author Edward Zengeni
 */
public interface CitizenshipRepository  extends CrudRepository<Citizenship, Long> {
    public List<Citizenship> findAll();
}
