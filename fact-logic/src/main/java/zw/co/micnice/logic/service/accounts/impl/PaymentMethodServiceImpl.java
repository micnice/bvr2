
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.PaymentMethodDAO;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;

/**
 *
 * @author Tatenda Chiwandire
 */
@Service
@Transactional(readOnly = true)
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodDAO paymentMethodDAO;

    public PaymentMethod save(PaymentMethod t) {
        return paymentMethodDAO.save(t);
    }

    public List<PaymentMethod> findAll() {
        return paymentMethodDAO.findAll();
    }

    public PaymentMethod get(Long id) {
        return paymentMethodDAO.get(id);
    }

    public void setPaymentMethodDao(PaymentMethodDAO paymentMethodDAO) {
        this.paymentMethodDAO = paymentMethodDAO;
    }

    public List<PaymentMethod> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
