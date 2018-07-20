package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.PaymentDetailsDAO;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;
import zw.co.micnice.logic.service.accounts.PaymentDetailsService;

/**
 *
 * @author Michael Matiashe
 */
@Service
@Transactional(readOnly = true)
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    @Autowired
    private PaymentDetailsDAO paymentDetailsDAO;

    public PaymentDetails save(PaymentDetails t) {
        return paymentDetailsDAO.save(t);
    }

    public List<PaymentDetails> findAll() {
        return paymentDetailsDAO.findAll();
    }

    public PaymentDetails get(Long id) {
        return paymentDetailsDAO.get(id);
    }

    public void setPaymentDetailsDao(PaymentDetailsDAO paymentDetailsDAO) {
        this.paymentDetailsDAO = paymentDetailsDAO;
    }

    public List<PaymentDetails> transactionHistory(Customer customer) {
        return paymentDetailsDAO.transactionHistory(customer);
    }


    public List<PaymentDetails> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
