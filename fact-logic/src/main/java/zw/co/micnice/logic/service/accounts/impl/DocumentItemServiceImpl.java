
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.DocumentItemDAO;
import zw.co.micnice.logic.domain.accounts.DocumentItem;
import zw.co.micnice.logic.service.accounts.DocumentItemService;

/**
 *
 * @author Tatenda Chiwandire
 */
@Service
@Transactional(readOnly = true)
public class DocumentItemServiceImpl implements DocumentItemService {

    @Autowired
    private DocumentItemDAO documentItemDAO;

    public DocumentItem save(DocumentItem t) {
        return documentItemDAO.save(t);
    }

    public List<DocumentItem> findAll() {
        return documentItemDAO.findAll();
    }

    public DocumentItem get(Long id) {
        return documentItemDAO.get(id);
    }

    public void setDocumentItemDao(DocumentItemDAO documentItemDAO) {
        this.documentItemDAO = documentItemDAO;
    }

    public List<DocumentItem> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
