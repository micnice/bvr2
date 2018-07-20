package zw.co.micnice.logic.dao.accounts;

import zw.co.micnice.logic.dao.IGenericDAO;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;

/**
 *
 * @author tdhlakama
 */
public interface CustomerDAO extends IGenericDAO<Customer> {

    public Customer get(Account account);
    
    public Registrant getRegistrant(Account account);
}
