
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.OldReceipt;


/**
 *
 * @author Michael Matiashe
 */
public interface OldReceiptRepository extends CrudRepository<OldReceipt, Long> {

    public List<OldReceipt> findAll();
}
