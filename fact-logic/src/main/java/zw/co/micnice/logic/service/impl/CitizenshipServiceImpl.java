
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.CitizenshipDAO;
import zw.co.micnice.logic.domain.Citizenship;
import zw.co.micnice.logic.service.CitizenshipService;


@Service
@Transactional(readOnly = true)
public class CitizenshipServiceImpl implements CitizenshipService {

    @Autowired
    private CitizenshipDAO citizenshipDAO;

    @Transactional
    public Citizenship save(Citizenship citizenship) {
        return citizenshipDAO.save(citizenship);
    }

    public List<Citizenship> findAll() {
        return citizenshipDAO.findAll();
    }

    public Citizenship get(Long id) {
        return citizenshipDAO.get(id);
    }

    /**
     * A DAO setter method to facilitate mocking
     *
     * @param citizenshipDAO
     */
    public void setCitizenshipDAO(CitizenshipDAO citizenshipDAO) {
        this.citizenshipDAO = citizenshipDAO;
    }
}
