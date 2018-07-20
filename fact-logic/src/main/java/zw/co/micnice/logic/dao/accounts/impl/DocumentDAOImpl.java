package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.DocumentDAO;
import zw.co.micnice.logic.dao.repo.DocumentRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentType;

/**
 *s
 * @author Michael Matiashe
 */
@Repository
public class DocumentDAOImpl implements DocumentDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DocumentRepository documentRepository;

    public Document save(Document t) {
        return documentRepository.save(t);
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document get(Long id) {
        return documentRepository.findOne(id);
    }

    public DocumentRepository getDocumentRepository() {
        return documentRepository;
    }

    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    
    public List<Document> getDocuments(Beneficiary beneficiary, DocumentType documentType){
        return entityManager.createQuery("select d from Document d where d.beneficiary=:beneficiary and d.documentType=:documentType").setParameter("beneficiary", beneficiary).setParameter("documentType", documentType).getResultList();
    }
}
