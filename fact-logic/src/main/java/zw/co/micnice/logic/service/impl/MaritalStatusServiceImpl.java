package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.MaritalStatusDAO;
import zw.co.micnice.logic.domain.MaritalStatus;
import zw.co.micnice.logic.service.MaritalStatusService;

/**
 *
 * @author Edaward Zengeni
 */
@Service
@Transactional(readOnly=true)
public class MaritalStatusServiceImpl implements MaritalStatusService{
    @Autowired
    private MaritalStatusDAO maritalStatusDAO;
    

    @Transactional
    public MaritalStatus save(MaritalStatus maritalStatus) {
       return maritalStatusDAO.save(maritalStatus);
    }

    public List<MaritalStatus> findAll() {
        return maritalStatusDAO.findAll();
    }

    public MaritalStatus get(Long id) {
       return maritalStatusDAO.get(id);
    }

    public void setMaritalStatusDAO(MaritalStatusDAO maritalStatusDAO) {
        this.maritalStatusDAO = maritalStatusDAO;
    }
    
    
    
}
