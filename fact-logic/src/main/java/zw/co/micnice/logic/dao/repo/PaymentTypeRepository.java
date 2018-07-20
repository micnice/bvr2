
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.PaymentType;


/**
 *
 * @author Michael Matiashe
 */
public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {

    public List<PaymentType> findAll();
}
