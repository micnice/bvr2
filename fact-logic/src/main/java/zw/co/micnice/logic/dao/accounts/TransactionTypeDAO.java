package zw.co.micnice.logic.dao.accounts;

import java.util.List;
import zw.co.micnice.logic.dao.IGenericDAO;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.TransactionType;



/**
 *
 * @author Charles Chigoriwa
 * @author Michael Matiashe
 */
public interface TransactionTypeDAO extends IGenericDAO<TransactionType> {
    public List<TransactionType> find(Book book);
    public List<TransactionType> find(Book book, PaymentMethod paymentMethod);  
    public List<TransactionType> findBankAccounts();
}
