package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Grade;

/**
 */
public interface GradeRepository extends CrudRepository<Grade, Long> {
    public List<Grade> findAll();
}
