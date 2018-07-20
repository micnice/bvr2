
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.DebtComponent;

/**
 *
 * @author Tatenda Chiwandire
 * @author Edward Zengeni
 */
public interface DebtComponentRepository extends CrudRepository<DebtComponent, Long> {

    public List<DebtComponent> findAll();
}
