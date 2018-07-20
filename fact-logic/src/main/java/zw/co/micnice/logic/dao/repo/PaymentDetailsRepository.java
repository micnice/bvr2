
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;

/**
 *
 * @author Michael Matiashe
 */
public interface PaymentDetailsRepository extends CrudRepository<PaymentDetails, Long> {

    public List<PaymentDetails> findAll();
}
