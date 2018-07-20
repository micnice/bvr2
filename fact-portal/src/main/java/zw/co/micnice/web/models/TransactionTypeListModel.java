/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;

/**
 *
 * @author Michael Matiashe
 */
public class TransactionTypeListModel extends LoadableDetachableModel<List<TransactionType>> {

    private final TransactionTypeService transactionTypeService;
    private final Book book;
    private PaymentMethod paymentMethod;

    public TransactionTypeListModel(TransactionTypeService transactionTypeService, Book book) {
        this.transactionTypeService = transactionTypeService;
        this.book = book;
    }

    public TransactionTypeListModel(TransactionTypeService transactionTypeService1, PaymentMethod paymentMethod, Book book1) {
        this.transactionTypeService = transactionTypeService1;
        this.paymentMethod = paymentMethod;
        this.book = book1;
    }

    @Override
    protected List<TransactionType> load() {
        if (book == null) {
            return transactionTypeService.findAll();
        } else if (paymentMethod == null) {
            return transactionTypeService.find(book);
        } else {
            return transactionTypeService.find(book, paymentMethod);
        }
    }
}
