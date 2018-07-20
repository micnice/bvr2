
package zw.co.micnice.logic.dao.accounts.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.BankDAO;
import zw.co.micnice.logic.dao.repo.BankRepository;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.AccountType;
import zw.co.micnice.logic.domain.accounts.Bank;


/**
 *
 *e
 * @author Michael Matiashe
 */
@Repository
public class BankDAOImpl implements BankDAO {
    
   

    @Autowired
    private BankRepository bankAccountRepository;

    public Bank save(Bank bankAccount) {
        if(bankAccount.getAccount()==null){
            Account account=new Account();
            account.setCode(bankAccount.getAccNumber());
            account.setBalance(BigDecimal.ZERO);
            account.setAccountType(AccountType.BANK);
            account.setName(bankAccount.getName());
            bankAccount.setAccount(account);
        }
        return bankAccountRepository.save(bankAccount);
    }

    public List<Bank> findAll() {
        return bankAccountRepository.findAll();
    }

    public Bank get(Long id) {
        return bankAccountRepository.findOne(id);
    }

    public BankRepository getBankAccountRepository() {
        return bankAccountRepository;
    }

    public void setBankAccountRepository(BankRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
   
}
