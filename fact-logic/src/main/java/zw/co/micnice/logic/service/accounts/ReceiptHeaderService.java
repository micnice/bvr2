package zw.co.micnice.logic.service.accounts;

import java.util.Date;
import java.util.List;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Michael Matiashe
 *
 */
public interface ReceiptHeaderService extends IGenericService<ReceiptHeader> {

    public List<ReceiptHeader> getReceiptHeaders(Long receiptNumber, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, TransactionType transactionType, Boolean reversed);
    
    public List<ReceiptHeader> getReceiptHeadersDeposit(Long receiptNumber, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, TransactionType transactionType, Boolean reversed);

    public List<ReceiptHeader> getReceiptNumbers();
    
}
