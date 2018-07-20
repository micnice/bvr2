
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.accounts.Document;

/**
 *
 * @author Michael Matiashe
 */
public interface DocumentRepository extends CrudRepository<Document, Long> {

    public List<Document> findAll();
}
