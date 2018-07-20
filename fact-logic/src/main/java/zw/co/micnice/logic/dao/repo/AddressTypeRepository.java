package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.AddressType;

/**
 */
public interface AddressTypeRepository extends CrudRepository<AddressType, Long> {
    public List<AddressType> findAll();
}
