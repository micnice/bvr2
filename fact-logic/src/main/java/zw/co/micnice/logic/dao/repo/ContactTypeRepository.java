package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.ContactType;

/**
 *
 * @author Charles Chigoriwa
 */
public interface ContactTypeRepository extends CrudRepository<ContactType, Long> {
    public List<ContactType> findAll();
}
