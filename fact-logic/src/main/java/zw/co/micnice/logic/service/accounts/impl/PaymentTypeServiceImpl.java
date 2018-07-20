
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.PaymentTypeDAO;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.service.accounts.PaymentTypeService;

/**
 *
 * @author Tatenda Chiwandire
 */
@Service
@Transactional(readOnly = true)
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeDAO paymentTypeDAO;

    public PaymentType save(PaymentType t) {
        return paymentTypeDAO.save(t);
    }

    public List<PaymentType> findAll() {
        return paymentTypeDAO.findAll();
    }

    public PaymentType get(Long id) {
        return paymentTypeDAO.get(id);
    }

    public void setPaymentTypeDao(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }

    public List<PaymentType> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
