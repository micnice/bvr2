package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.AccountsParametersDAO;
import zw.co.micnice.logic.dao.repo.AccountsParametersRepository;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;

/**
 *
 * @author kelvin goredema
 */
@Repository
public class AccountsParametersDAOImpl implements AccountsParametersDAO {

    @Autowired
    private AccountsParametersRepository accountsParametersRepository;

    public AccountsParameters save(AccountsParameters accountsParameters) {
        return accountsParametersRepository.save(accountsParameters);
    }

    public List<AccountsParameters> findAll() {
        return accountsParametersRepository.findAll();
    }

    public AccountsParameters get(Long id) {
        return accountsParametersRepository.findOne(id);
    }
    /*
     * A setter method that will make mocking repo object easier
     * @param  TransactionType
     */

    public void setAccountsParametersRepository(AccountsParametersRepository accountsParametersRepository) {
        this.accountsParametersRepository = accountsParametersRepository;
    }
}
