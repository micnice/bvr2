
package zw.co.micnice.logic.dao.accounts;

import java.util.List;
import zw.co.micnice.logic.dao.IGenericDAO;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
public interface PaymentDetailsDAO extends IGenericDAO<PaymentDetails> {
   public  List<PaymentDetails> transactionHistory(Customer customer);
}
