
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.Account;

/**
 *
 * @author Tatenda Chiwandire
 * @author Edward Zengeni
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    public List<Account> findAll();
}
