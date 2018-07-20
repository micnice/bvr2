package zw.co.micnice.logic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.DurationDAO;
import zw.co.micnice.logic.dao.repo.DurationRepository;
import zw.co.micnice.logic.domain.Duration;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class DurationDAOImpl implements DurationDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private DurationRepository durationRepository;

    public Duration save(Duration duration) {
        return durationRepository.save(duration); //Make sure every course is in one active period only!
    }

    public List<Duration> findAll() {
        return durationRepository.findAll();
    }

    public Duration get(Long id) {
        return durationRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param durationRepository
     */
    public void setDurationRepository(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    
}
