package zw.co.micnice.logic.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.ReceiptHeaderDAO;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.ReceiptHeaderService;

/**
 *
 * @author Tatenda Chiwandire
 */
@Service
@Transactional(readOnly = true)
public class ReceiptHeaderServiceImpl implements ReceiptHeaderService {

    @Autowired
    private ReceiptHeaderDAO receiptHeaderDAO;

    public ReceiptHeader save(ReceiptHeader t) {
        return receiptHeaderDAO.save(t);
    }

    public List<ReceiptHeader> findAll() {
        return receiptHeaderDAO.findAll();
    }

    public ReceiptHeader get(Long id) {
        return receiptHeaderDAO.get(id);
    }

    public void setReceiptHeaderDao(ReceiptHeaderDAO receiptHeaderDAO) {
        this.receiptHeaderDAO = receiptHeaderDAO;
    }

    public List<ReceiptHeader> getReceiptHeaders(Long receiptNumber, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, TransactionType transactionType, Boolean reversed) {
        return receiptHeaderDAO.getReceiptHeaders(receiptNumber, paymentMethod, paymentType, startDate, endDate, user, transactionType, reversed);
    }

    public List<ReceiptHeader> getReceiptNumbers() {
        return receiptHeaderDAO.getReceiptNumbers(null);
    }

    public List<ReceiptHeader> getReceiptHeadersDeposit(Long receiptNumber, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, TransactionType transactionType, Boolean reversed) {
        return receiptHeaderDAO.getReceiptHeadersDeposit(receiptNumber, paymentMethod, paymentType, startDate, endDate, user, transactionType, reversed);
    }
}
