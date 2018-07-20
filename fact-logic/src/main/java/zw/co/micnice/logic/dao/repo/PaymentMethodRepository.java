
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;


/**
 *
 * @author  Michael Matiashe
 */
public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {

    public List<PaymentMethod> findAll();
}
