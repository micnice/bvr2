package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.MaritalStatusDAO;
import zw.co.micnice.logic.dao.repo.MaritalStatusRepository;
import zw.co.micnice.logic.domain.MaritalStatus;

/**
 *
 * @author Edawrd Zengeni
 */
@Repository
public class MaritalStatusDAOImpl implements MaritalStatusDAO {

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    public MaritalStatus save(MaritalStatus maritalStatus) {
        return maritalStatusRepository.save(maritalStatus);
    }

    public List<MaritalStatus> findAll() {
        return maritalStatusRepository.findAll();
    }

    public MaritalStatus get(Long id) {
        return maritalStatusRepository.findOne(id);
    }

    public void setMaritalStatusRepository(MaritalStatusRepository maritalStatusRepository) {
        this.maritalStatusRepository = maritalStatusRepository;
    }
}
