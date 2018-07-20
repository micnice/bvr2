
package zw.co.micnice.logic.service.accounts;

import java.util.List;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Michael Matiashe
 */
public interface PaymentDetailsService extends IGenericService<PaymentDetails> {
    public List<PaymentDetails> transactionHistory(Customer customer);
}
