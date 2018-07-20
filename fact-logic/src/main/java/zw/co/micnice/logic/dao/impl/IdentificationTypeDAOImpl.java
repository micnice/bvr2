package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.IdentificationTypeDAO;
import zw.co.micnice.logic.dao.repo.IdentificationTypeRepository;
import zw.co.micnice.logic.domain.IdentificationType;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class IdentificationTypeDAOImpl implements IdentificationTypeDAO {

    @Autowired
    private IdentificationTypeRepository identificationTypeRepository;

    public IdentificationType save(IdentificationType identificationType) {
        return identificationTypeRepository.save(identificationType);
    }

    public List<IdentificationType> findAll() {
        return identificationTypeRepository.findAll();
    }

    public IdentificationType get(Long id) {
        return identificationTypeRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     * @param identificationTypeRepository 
     */
    public void setIdentificationTypeRepository(IdentificationTypeRepository identificationTypeRepository) {
        this.identificationTypeRepository = identificationTypeRepository;
    }

    
    
}
