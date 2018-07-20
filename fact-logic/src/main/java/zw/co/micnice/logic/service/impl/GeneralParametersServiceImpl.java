package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.GeneralParametersDAO;
import zw.co.micnice.logic.domain.GeneralParameters;
import zw.co.micnice.logic.service.GeneralParametersService;

/**
 *
 * @author Edward Zengeni
 */
@Service
@Transactional(readOnly = true)
public class GeneralParametersServiceImpl implements GeneralParametersService {

    @Autowired
    private GeneralParametersDAO generalParameterDAO;

    @Transactional
    public GeneralParameters save(GeneralParameters generalParameters) {
        List<GeneralParameters> list = findAll();
        if (generalParameters.getId() == null && !list.isEmpty()) {
            throw new IllegalStateException("Only one row is allowed for parameters!!!!!");
        } else if (generalParameters.getId() != null && get(generalParameters.getId()) == null) {
            throw new IllegalStateException("Row does not exist");
        } else if (list.size() >= 2) {
            throw new IllegalStateException("Only one row is allowed for parameters!!!!!");
        }
        return generalParameterDAO.save(generalParameters);
    }

    public List<GeneralParameters> findAll() {
        return generalParameterDAO.findAll();
    }

    public GeneralParameters get(Long id) {
        return generalParameterDAO.get(id);
    }

    public GeneralParameters get() {
        List<GeneralParameters> parameters = generalParameterDAO.findAll();
        if (parameters.isEmpty()) {
            return new GeneralParameters();
        } else if (parameters.size()== 1) {
            return parameters.get(0);
        } else {
            throw new IllegalStateException("Only one row is allowed for parameters!!!!!");
        }
    }

    /**
     * A DAO setter method to facilitate mocking
     *
     * @param generalParameterDAO
     */
    public void setGeneralParameterDAO(GeneralParametersDAO generalParameterDAO) {
        this.generalParameterDAO = generalParameterDAO;
    }
}
