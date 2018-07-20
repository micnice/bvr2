
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.DocumentItem;

/**
 *
 * @author Michael Matiashe
 */
public interface DocumentItemRepository extends CrudRepository<DocumentItem, Long> {

    public List<DocumentItem> findAll();
}
