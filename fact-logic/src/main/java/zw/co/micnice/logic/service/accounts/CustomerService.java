/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.accounts;

import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author tdhlakama
 */
public interface CustomerService extends IGenericService<Customer> {

    public Customer get(Account account);

    public Registrant getRegistrant(Account account);

}
