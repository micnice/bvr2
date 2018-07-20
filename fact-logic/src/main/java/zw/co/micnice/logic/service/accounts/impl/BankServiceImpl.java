/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.BankDAO;
import zw.co.micnice.logic.domain.accounts.Bank;
import zw.co.micnice.logic.service.accounts.BankService;
/**
 *
 * @author Michael Matiashe
 */

@Service
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDAO bankAccountDAO;

    public Bank save(Bank t) {
        return bankAccountDAO.save(t);
    }

    public List<Bank> findAll() {
        return bankAccountDAO.findAll();
    }

    public Bank get(Long id) {
        return bankAccountDAO.get(id);
    }

    public void setBankAccountDao(BankDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    public List<Bank> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
