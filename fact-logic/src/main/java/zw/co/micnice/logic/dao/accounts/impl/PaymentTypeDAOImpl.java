
package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.PaymentTypeDAO;
import zw.co.micnice.logic.dao.repo.PaymentTypeRepository;
import zw.co.micnice.logic.domain.accounts.PaymentType;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class PaymentTypeDAOImpl implements PaymentTypeDAO {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    public PaymentType save(PaymentType t) {
        return paymentTypeRepository.save(t);
    }

    public List<PaymentType> findAll() {
        return paymentTypeRepository.findAll();
    }

    public PaymentType get(Long id) {
        return paymentTypeRepository.findOne(id);
    }

    public PaymentTypeRepository getPaymentTypeRepository() {
        return paymentTypeRepository;
    }

    public void setPaymentTypeRepository(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }
}
