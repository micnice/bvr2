
package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.PaymentMethodDAO;
import zw.co.micnice.logic.dao.repo.PaymentMethodRepository;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
@Repository
public class PaymentMethodDAOImpl implements PaymentMethodDAO {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod save(PaymentMethod t) {
        return paymentMethodRepository.save(t);
    }

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod get(Long id) {
        return paymentMethodRepository.findOne(id);
    }

    public PaymentMethodRepository getPaymentMethodRepository() {
        return paymentMethodRepository;
    }

    public void setPaymentMethodRepository(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }
 
}
