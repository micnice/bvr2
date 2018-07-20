
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.AccountDAO;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.service.accounts.AccountService;

/**
 *
 * @author Tatenda Chiwandire
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public Account save(Account t) {
        return accountDAO.save(t);
    }

    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    public Account get(Long id) {
        return accountDAO.get(id);
    }

    public void setAccountDao(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Account> getGeneralLedgerAccounts() {
        return accountDAO.getGLAccounts();
    }

    public List<Account> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
