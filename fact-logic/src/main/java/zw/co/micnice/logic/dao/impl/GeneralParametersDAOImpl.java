package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.GeneralParametersDAO;
import zw.co.micnice.logic.dao.repo.GeneralParametersRepository;
import zw.co.micnice.logic.domain.GeneralParameters;

/**
 *
 * @author Edward Zengeni
 */
@Repository
public class GeneralParametersDAOImpl implements GeneralParametersDAO {

    @Autowired
    private GeneralParametersRepository generalParameterRepository;

    public GeneralParameters save(GeneralParameters generalParameter) {
        return generalParameterRepository.save(generalParameter);
    }

    public List<GeneralParameters> findAll() {
        return generalParameterRepository.findAll();
    }

    public GeneralParameters get(Long id) {
        return generalParameterRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     * @param generalParameterRepository 
     */
    public void setGeneralParameterRepository(GeneralParametersRepository generalParameterRepository) {
        this.generalParameterRepository = generalParameterRepository;
    }

    
    
}
