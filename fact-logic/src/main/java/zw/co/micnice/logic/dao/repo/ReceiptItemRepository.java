
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;


/**
 *
 * @author Michael Matiashe
 */
public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long> {

    public List<ReceiptItem> findAll();
}
