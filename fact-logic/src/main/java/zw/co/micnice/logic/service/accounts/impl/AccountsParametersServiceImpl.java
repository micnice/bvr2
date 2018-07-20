package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.AccountsParametersDAO;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;

/**
 *
 * @author Kelvin Goredema
 */
@Service
@Transactional(readOnly = true)
public class AccountsParametersServiceImpl implements AccountsParametersService {

    @Autowired
    private AccountsParametersDAO accountsParametersDAO;

    @Transactional
    public AccountsParameters save(AccountsParameters accountsParameters) {
        List<AccountsParameters> list=findAll();
        if (accountsParameters.getId() == null && !list.isEmpty()) {
            throw new IllegalStateException("Only one row is allowed for parameters!!!!!");
        }else if(accountsParameters.getId()!=null && get(accountsParameters.getId())==null){
            throw new IllegalStateException("Row does not exist");
        }else if(list.size()>=2){
            throw new IllegalStateException("Only one row is allowed for parameters!!!!!");
        }
        return accountsParametersDAO.save(accountsParameters);
    }

    public List<AccountsParameters> findAll() {
        return accountsParametersDAO.findAll();
    }

    public AccountsParameters get(Long id) {
        return accountsParametersDAO.get(id);
    }
    
     public AccountsParameters get() {
        List<AccountsParameters> parameters = accountsParametersDAO.findAll();
        if (parameters.isEmpty()) {
            return new AccountsParameters();
        } else if (parameters.size()== 1) {
            return parameters.get(0);
        } else {
            throw new IllegalStateException("Only one row is allowed for parameters!!!!!");
        }
    }

    public void setAccountsParametersDAO(AccountsParametersDAO accountsParametersDAO) {
        this.accountsParametersDAO = accountsParametersDAO;
    }

    public List<AccountsParameters> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
