package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.TransactionType;

/**
 *
 * @author Charles Chigoriwa
 */
public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {
    public List<TransactionType> findAll();
}
