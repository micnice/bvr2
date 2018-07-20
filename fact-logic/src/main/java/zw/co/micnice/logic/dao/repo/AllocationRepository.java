
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.Allocation;

/**
 *
 * @author Constance Mabaso
 */
public interface AllocationRepository extends CrudRepository<Allocation, Long> {

    public List<Allocation> findAll();
}
