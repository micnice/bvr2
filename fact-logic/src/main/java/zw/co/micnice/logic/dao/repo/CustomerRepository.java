package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.Customer;

/**
 *
 * @author Takunda Dhlakama
 * @author Michael Matiashe
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public List<Customer> findAll();
}
