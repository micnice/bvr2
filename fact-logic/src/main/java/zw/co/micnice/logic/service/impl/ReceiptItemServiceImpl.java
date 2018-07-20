package zw.co.micnice.logic.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.ReceiptItemDAO;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.ReceiptItemService;

/**
 *
 * @author Michael Matiashe
 */
@Service
@Transactional(readOnly = true)
public class ReceiptItemServiceImpl implements ReceiptItemService {

    @Autowired
    private ReceiptItemDAO receiptItemDAO;

    public ReceiptItem save(ReceiptItem t) {
        return receiptItemDAO.save(t);
    }

    public List<ReceiptItem> findAll() {
        return receiptItemDAO.findAll();
    }

    public ReceiptItem get(Long id) {
        return receiptItemDAO.get(id);
    }

    public void setReceiptItemDao(ReceiptItemDAO receiptItemDAO) {
        this.receiptItemDAO = receiptItemDAO;
    }

    public List<ReceiptItem> getReceiptItems(Product product, Date startDate, Date endDate, TransactionType transactionType) {
        return receiptItemDAO.getReceiptItems(product, startDate, endDate, transactionType);
    }

    public List<ReceiptItem> getReceiptItemsDeposit(Product product, Date startDate, Date endDate, TransactionType transactionType) {
        return receiptItemDAO.getReceiptItemsDeposit(product, startDate, endDate, transactionType);
    }

    public ReceiptItem get(DebtComponent debtComponent) {
        return receiptItemDAO.get(debtComponent);
    }

    public List<ReceiptItem> getReceiptItems(Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, TransactionType transactionType, User user) {
        return receiptItemDAO.getReceiptItems(product, paymentMethod, paymentType, startDate, endDate, transactionType, user);
    }

    public List<ReceiptItem> getReceiptItemsWithNoReceiptHeader(Account account) {
        return receiptItemDAO.getReceiptItemsWithNoReceiptHeader(account);
    }

    public List<ReceiptItem> getIncomeGenerated(Date startDate, Date endDate) {
        return receiptItemDAO.getIncomeGenerated(startDate, endDate);
    }
}