package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.TransactionTypeDAO;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;

/**
 *
 * @author Michael Matiashe
 */
@Service
@Transactional(readOnly = true)
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeDAO transactionTypeDAO;

    @Transactional
    public TransactionType save(TransactionType transactionType) {
        return transactionTypeDAO.save(transactionType);
    }

    public List<TransactionType> findAll() {
        return transactionTypeDAO.findAll();
    }

    public TransactionType get(Long id) {
        return transactionTypeDAO.get(id);
    }

    public void setTransactionTypeDAO(TransactionTypeDAO transactionTypeDAO) {
        this.transactionTypeDAO = transactionTypeDAO;
    }

    public List<TransactionType> find(Book book) {
        return this.transactionTypeDAO.find(book);
    }

    public List<TransactionType> findBankAccounts() {
        return transactionTypeDAO.findBankAccounts();
    }

    public List<TransactionType> find(Book book, PaymentMethod paymentMethod) {
        return transactionTypeDAO.find(book, paymentMethod);
    }

    public List<TransactionType> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
