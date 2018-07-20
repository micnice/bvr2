/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;

/**
 *
 * @author Michael Matiashe
 */
public class AccountsParametersListModel extends LoadableDetachableModel<List<AccountsParameters>> {

    private final AccountsParametersService accountsParametersService;

    public AccountsParametersListModel(AccountsParametersService accountsParametersService) {
        this.accountsParametersService = accountsParametersService;
    }

    @Override
    protected List<AccountsParameters> load() {
        return accountsParametersService.findAll();
    }
}
