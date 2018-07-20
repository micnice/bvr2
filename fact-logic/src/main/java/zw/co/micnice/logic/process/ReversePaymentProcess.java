package zw.co.micnice.logic.process;

import java.io.Serializable;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;

/**
 *
 * @author Matiashe Michael
 */
public interface ReversePaymentProcess extends Serializable {

    public ReceiptHeader reversePayment(ReceiptHeader receiptHeader);

}
