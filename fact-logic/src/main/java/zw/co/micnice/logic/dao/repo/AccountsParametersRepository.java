
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;

/**
 *
 * @author Kelvin Goredema
 */
public interface AccountsParametersRepository   extends CrudRepository<AccountsParameters, Long>{
    
    public List<AccountsParameters> findAll();
}
