package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.DurationDAO;
import zw.co.micnice.logic.domain.Duration;
import zw.co.micnice.logic.service.DurationService;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
@Transactional(readOnly = true)
public class DurationServiceImpl implements DurationService {

    @Autowired
    private DurationDAO durationDAO;

    @Transactional
    public Duration save(Duration duration) {
        return durationDAO.save(duration);
    }

    public List<Duration> findAll() {
        return durationDAO.findAll();
    }

    public Duration get(Long id) {
        return durationDAO.get(id);
    }

    public void setDurationDAO(DurationDAO durationDAO) {
        this.durationDAO = durationDAO;
    }
}
