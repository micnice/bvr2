/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.SupportDocumentDAO;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.SupportDocument;
import zw.co.micnice.logic.service.SupportDocumentService;

/**
 *
 * @author kelvin
 */
@Service
@Transactional(readOnly = true)
public class SupportDocumentServiceImpl implements SupportDocumentService {

    @Autowired
    private SupportDocumentDAO supportDocumentDAO;

    @Transactional
    public SupportDocument save(SupportDocument supportDocument) {
        return supportDocumentDAO.save(supportDocument);
    }

    public List<SupportDocument> findAll() {
        return supportDocumentDAO.findAll();
    }

    public SupportDocument get(Long id) {
        return supportDocumentDAO.get(id);
    }

    public void setSupportDocumentDAO(SupportDocumentDAO supportDocumentDAO) {
        this.supportDocumentDAO = supportDocumentDAO;
    }

   
}
