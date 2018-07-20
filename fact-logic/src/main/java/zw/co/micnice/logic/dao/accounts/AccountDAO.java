
package zw.co.micnice.logic.dao.accounts;

import java.util.List;
import zw.co.micnice.logic.dao.IGenericDAO;
import zw.co.micnice.logic.domain.accounts.Account;

/**
 *
 * @author Michael Matiashe
 */
public interface AccountDAO extends IGenericDAO<Account> {
    
    public List<Account> getGLAccounts();
    
}
