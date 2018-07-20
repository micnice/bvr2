package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.GeneralParameters;

/**
 *
 * @author Edward Zengeni
 */
public interface GeneralParametersRepository extends CrudRepository<GeneralParameters, Long> {

    public List<GeneralParameters> findAll();
}
