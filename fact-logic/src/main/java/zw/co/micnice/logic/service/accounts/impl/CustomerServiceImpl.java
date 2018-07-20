/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.CustomerDAO;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.service.accounts.CustomerService;

/**
 *
 * @author tdhlakama
 */
@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerAccountDAO;

    @Transactional
    public Customer save(Customer customerAccount) {
        return customerAccountDAO.save(customerAccount);
    }

    public List<Customer> findAll() {
        return customerAccountDAO.findAll();

    }

    public Customer get(Long id) {
        return customerAccountDAO.get(id);
    }

    public void setCustomerAccountDAO(CustomerDAO customerAccountDAO) {
        this.customerAccountDAO = customerAccountDAO;
    }

    public Customer get(Account account) {
        return customerAccountDAO.get(account);
    }

    public Registrant getRegistrant(Account account) {
        return customerAccountDAO.getRegistrant(account);
    }

    public List<Customer> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
