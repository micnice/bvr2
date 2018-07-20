
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.DocumentDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentType;
import zw.co.micnice.logic.service.accounts.DocumentService;

/**
 *
 * @author Michael Matiashe
 */
@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    public Document save(Document t) {
        return documentDAO.save(t);
    }

    public List<Document> findAll() {
        return documentDAO.findAll();
    }

    public Document get(Long id) {
        return documentDAO.get(id);
    }

    public void setDocumentDao(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public List<Document> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Document> getDocuments(Beneficiary beneficiary, DocumentType documentType) {
        return documentDAO.getDocuments(beneficiary, documentType);
    }


}
