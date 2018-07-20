package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Duration;

/**
 *
 * @author Michael Matiashe
 */
public interface DurationRepository extends CrudRepository<Duration, Long> {
    public List<Duration> findAll();
}
