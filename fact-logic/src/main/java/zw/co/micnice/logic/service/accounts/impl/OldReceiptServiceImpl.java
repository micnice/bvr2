package zw.co.micnice.logic.service.accounts.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.OldReceiptDAO;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.OldReceipt;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.service.accounts.OldReceiptService;

/**
 *
 * @author Tatenda Chiwandire
 */
@Service
@Transactional(readOnly = true)
public class OldReceiptServiceImpl implements OldReceiptService {

    @Autowired
    private OldReceiptDAO oldReceiptDAO;

    public OldReceipt save(OldReceipt t) {
        return oldReceiptDAO.save(t);
    }

    public List<OldReceipt> findAll() {
        return oldReceiptDAO.findAll();
    }

    public OldReceipt get(Long id) {
        return oldReceiptDAO.get(id);
    }

    public void setOldReceiptDao(OldReceiptDAO oldReceiptDAO) {
        this.oldReceiptDAO = oldReceiptDAO;
    }

    public List<OldReceipt> getReceipts(Long receiptNumber, Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, Boolean cancel, Boolean bank) {
        return oldReceiptDAO.getReceipts(receiptNumber, product, paymentMethod, paymentType, startDate, endDate, user, cancel, bank);
    }

    public List<OldReceipt> getReceipts(Product product, Date startDate, Date endDate, Boolean canceled) {
        return oldReceiptDAO.getReceipts(product, startDate, endDate, canceled);
    }

    public List<OldReceipt> getReceipts(Product product, Registrant registrant) {
        return oldReceiptDAO.getReceipts(product, registrant);
    }

    public List<OldReceipt> getOldRenewalReceipts() {
        return oldReceiptDAO.getOldRenewalReceipts();
    }

    public Double getReceiptTotal(Long receiptNumber, Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, Boolean cancel, Boolean bank) {
        return oldReceiptDAO.getReceiptTotal(receiptNumber, product, paymentMethod, paymentType, startDate, endDate, user, cancel, bank);
    }

    public Double getReceiptTotal(Product product, Date startDate, Date endDate, Boolean cancel) {
        return oldReceiptDAO.getReceiptTotal(product, startDate, endDate, cancel);
    }

    public List<OldReceipt> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}