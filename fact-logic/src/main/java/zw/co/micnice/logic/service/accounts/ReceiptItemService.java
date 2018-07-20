package zw.co.micnice.logic.service.accounts;

import java.util.Date;
import java.util.List;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Michael Matiashe
 *
 */
public interface ReceiptItemService extends IGenericService<ReceiptItem> {

    public List<ReceiptItem> getReceiptItems(Product product, Date startDate, Date endDate, TransactionType transactionType);
    
    public List<ReceiptItem> getReceiptItemsDeposit(Product product, Date startDate, Date endDate, TransactionType transactionType);

    public ReceiptItem get(DebtComponent debtComponent);

    public List<ReceiptItem> getReceiptItemsWithNoReceiptHeader(Account account);

    public List<ReceiptItem> getIncomeGenerated(Date startDate, Date endDate);

    public List<ReceiptItem> getReceiptItems(Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, TransactionType transactionType, User user);
}
