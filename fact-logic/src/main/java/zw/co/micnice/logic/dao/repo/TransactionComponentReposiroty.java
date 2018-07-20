package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.TransactionComponent;

/**
 *
 * @author Judge Muzinda
 * @author Constance Mabaso
 */
public interface TransactionComponentReposiroty extends CrudRepository<TransactionComponent, Long> {
    
    public List<TransactionComponent> findAll();
}
