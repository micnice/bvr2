
package zw.co.micnice.logic.service.accounts;

import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentType;
import zw.co.micnice.logic.service.IGenericService;


/**
 *
 * @author Michael 

 */
public interface DocumentService extends IGenericService<Document> {
    
    public List<Document> getDocuments(Beneficiary beneficiary, DocumentType documentType);
    
}
