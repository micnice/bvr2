package zw.co.micnice.logic.dao.accounts;

import java.util.List;
import zw.co.micnice.logic.dao.IGenericDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentType;

/**
 *
 * @author Michael Matiashe
 */
public interface DocumentDAO extends IGenericDAO<Document> {
    public List<Document> getDocuments(Beneficiary beneficiary, DocumentType documentType);
}
