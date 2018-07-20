
package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.DocumentItemDAO;
import zw.co.micnice.logic.dao.repo.DocumentItemRepository;
import zw.co.micnice.logic.domain.accounts.DocumentItem;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
@Repository
public class DocumentItemDAOImpl implements DocumentItemDAO {

    @Autowired
    private DocumentItemRepository documentItemRepository;

    public DocumentItem save(DocumentItem t) {
        return documentItemRepository.save(t);
    }

    public List<DocumentItem> findAll() {
        return documentItemRepository.findAll();
    }

    public DocumentItem get(Long id) {
        return documentItemRepository.findOne(id);
    }

    public DocumentItemRepository getDocumentItemRepository() {
        return documentItemRepository;
    }

    public void setDocumentItemRepository(DocumentItemRepository documentItemRepository) {
        this.documentItemRepository = documentItemRepository;
    }
    
}
