package zw.co.micnice.logic.service.accounts;

import java.util.List;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Charles Chigoriwa
 */
public interface TransactionTypeService extends IGenericService<TransactionType> {
    public List<TransactionType> find(Book book);
    public List<TransactionType> find(Book book, PaymentMethod paymentMethod);
    public List<TransactionType> findBankAccounts();
}
