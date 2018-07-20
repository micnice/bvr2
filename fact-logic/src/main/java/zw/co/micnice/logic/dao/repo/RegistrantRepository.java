package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Registrant;

/**
 *
 * @author Takunda Dhlakama
 * @author Michael Matiashe
 */
public interface RegistrantRepository extends CrudRepository<Registrant, Long> {

    public List<Registrant> findAll();
}
