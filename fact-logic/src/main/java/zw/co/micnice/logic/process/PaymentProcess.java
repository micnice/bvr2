package zw.co.micnice.logic.process;

import java.io.Serializable;
import java.util.List;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;

/**
 *
 * @author Michael Matiashe
 */
public interface PaymentProcess extends Serializable {
    public PaymentDetails processPayment(PaymentDetails paymentDetails, List<ReceiptItem> receiptItems);
}
