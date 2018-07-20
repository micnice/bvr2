package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.RegistrantActivityDAO;
import zw.co.micnice.logic.domain.Duration;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantActivity;
import zw.co.micnice.logic.domain.RegistrantActivityType;
import zw.co.micnice.logic.service.RegistrantActivityService;

/**
 *
 * @author Kelvin Goredema
 */
@Service
@Transactional(readOnly = true)
public class RegistrantActivityServiceImpl implements RegistrantActivityService {

    @Autowired
    private RegistrantActivityDAO registrantActivityDAO;

    @Transactional
    public RegistrantActivity save(RegistrantActivity registrantActivity) {
        return registrantActivityDAO.save(registrantActivity);
    }

    public List<RegistrantActivity> findAll() {
        return registrantActivityDAO.findAll();
    }

    public RegistrantActivity get(Long id) {
        return registrantActivityDAO.get(id);
    }

    public void setRegistrantActivityDAO(RegistrantActivityDAO registrantActivityDAO) {
        this.registrantActivityDAO = registrantActivityDAO;
    }

    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant, RegistrantActivityType registrantActivityType) {
        return registrantActivityDAO.getRegistrantActivities(registrant, registrantActivityType);
    }

    public RegistrantActivity getRegistrantActivityByDuration(Registrant registrant, Duration duration) {
        return registrantActivityDAO.getRegistrantActivityByDuration(registrant, duration);
    }

    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration) {
        return registrantActivityDAO.getRegistrantActivities(registrant, registrantActivityType, duration);
    }

    public RegistrantActivity getRegistrantActivity(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration) {
        return registrantActivityDAO.getRegistrantActivity(registrant, registrantActivityType, duration);
    }

    public List<Registrant> getRegistrantActivities(RegistrantActivityType registrantActivityType, Duration duration) {
        return registrantActivityDAO.getRegistrantActivities(registrantActivityType, duration);
    }
}
