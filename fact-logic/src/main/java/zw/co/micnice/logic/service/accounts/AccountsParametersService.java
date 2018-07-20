
package zw.co.micnice.logic.service.accounts;

import zw.co.micnice.logic.domain.accounts.AccountsParameters;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Kelvin Goredema
 */
public interface AccountsParametersService extends IGenericService<AccountsParameters>{
    public AccountsParameters get();
}
