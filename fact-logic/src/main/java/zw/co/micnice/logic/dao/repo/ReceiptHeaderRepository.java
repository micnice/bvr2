
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;

/**
 *
 * @author Michael Matiashe
 */
public interface ReceiptHeaderRepository extends CrudRepository<ReceiptHeader, Long> {

    public List<ReceiptHeader> findAll();
    
}
