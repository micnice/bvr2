
package zw.co.micnice.logic.service.accounts;

import java.util.List;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Tatenda Chiwandire
 * @author Kelvin Goredema
 * @author Charles Chigoriwa

 */
public interface AccountService extends IGenericService<Account> {
    public List<Account> getGeneralLedgerAccounts();
}
