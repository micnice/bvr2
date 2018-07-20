package zw.co.micnice.logic.dao.impl;

/**
 *
 * @author Edward Zengeni
 * @author Charles Chigoriwa
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.CitizenshipDAO;
import zw.co.micnice.logic.dao.repo.CitizenshipRepository;
import zw.co.micnice.logic.domain.Citizenship;

@Repository
public class CitizenshipDAOImpl implements CitizenshipDAO {

    @Autowired
    private CitizenshipRepository citizenshipRepository;

    public Citizenship save(Citizenship citizenship) {
        return citizenshipRepository.save(citizenship);
    }

    public List<Citizenship> findAll() {
        return citizenshipRepository.findAll();
    }

    public Citizenship get(Long id) {
        return citizenshipRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param citizenshipRepository
     */
    public void setCitizenshipRepository(CitizenshipRepository citizenshipRepository) {
        this.citizenshipRepository = citizenshipRepository;
    }
}
