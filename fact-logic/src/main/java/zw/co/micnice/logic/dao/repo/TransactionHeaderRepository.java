
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.TransactionHeader;

/**
 *
 * @author Edward Zengeni
 */
public interface TransactionHeaderRepository  extends CrudRepository<TransactionHeader, Long>{
    public List<TransactionHeader> findAll();
}
