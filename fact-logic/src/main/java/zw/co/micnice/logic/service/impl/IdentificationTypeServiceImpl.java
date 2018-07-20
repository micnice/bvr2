package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.IdentificationTypeDAO;
import zw.co.micnice.logic.domain.IdentificationType;
import zw.co.micnice.logic.service.IdentificationTypeService;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
@Transactional(readOnly = true)
public class IdentificationTypeServiceImpl implements IdentificationTypeService {

    @Autowired
    private IdentificationTypeDAO identificationTypeDAO;

    @Transactional
    public IdentificationType save(IdentificationType identificationType) {
        return identificationTypeDAO.save(identificationType);
    }

    public List<IdentificationType> findAll() {
        return identificationTypeDAO.findAll();
    }

    public IdentificationType get(Long id) {
        return identificationTypeDAO.get(id);
    }

    /**
     * A DAO setter method to facilitate mocking
     * @param identificationTypeDAO 
     */
    public void setIdentificationTypeDAO(IdentificationTypeDAO identificationTypeDAO) {
        this.identificationTypeDAO = identificationTypeDAO;
    }

   
    
    
}
