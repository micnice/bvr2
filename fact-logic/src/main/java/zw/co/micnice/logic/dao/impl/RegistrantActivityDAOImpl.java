/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.RegistrantActivityDAO;
import zw.co.micnice.logic.dao.repo.RegistrantActivityRepository;
import zw.co.micnice.logic.domain.Duration;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantActivity;
import zw.co.micnice.logic.domain.RegistrantActivityType;

/**
 *
 * @author Kelvin Goredema
 */
@Repository
public class RegistrantActivityDAOImpl implements RegistrantActivityDAO {

    @Autowired
    private RegistrantActivityRepository registrantActivityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public RegistrantActivity save(RegistrantActivity registrantActivity) {
        return registrantActivityRepository.save(registrantActivity);
    }

    public List<RegistrantActivity> findAll() {
        return registrantActivityRepository.findAll();

    }

    public RegistrantActivity get(Long id) {
        return registrantActivityRepository.findOne(id);

    }

    public void setRegistrantActivityRepository(RegistrantActivityRepository registrantActivityRepository) {
        this.registrantActivityRepository = registrantActivityRepository;
    }

    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant, RegistrantActivityType registrantActivityType) {
        return this.entityManager.createQuery("Select a from RegistrantActivity a where a.registrant=:registrant"
                + " and a.registrantActivityType=:registrantActivityType")
                .setParameter("registrant", registrant)
                .setParameter("registrantActivityType", registrantActivityType)
                .getResultList();
    }

    public RegistrantActivity getRegistrantActivityByDuration(Registrant registrant, Duration duration) {
        List<RegistrantActivity> registrantActivities = this.entityManager.createQuery("Select a from RegistrantActivity a where a.registrant=:registrant"
                + " and a.duration=:duration")
                .setParameter("registrant", registrant)
                .setParameter("duration", duration)
                .getResultList();
        if (!registrantActivities.isEmpty() || registrantActivities.size() > 0) {
            return registrantActivities.get(0);
        } else {
            return null;
        }
    }

    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration) {
        return this.entityManager.createQuery("Select a from RegistrantActivity a where a.registrant=:registrant"
                + " and a.registrantActivityType=:registrantActivityType"
                + " and a.duration=:duration")
                .setParameter("registrant", registrant)
                .setParameter("registrantActivityType", registrantActivityType)
                .getResultList();
    }

    public RegistrantActivity getRegistrantActivity(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration) {
        List<RegistrantActivity> registrantActivities = this.entityManager.createQuery("Select a from RegistrantActivity a where a.registrant=:registrant"
                + " and a.duration=:duration")
                .setParameter("registrant", registrant)
                .setParameter("duration", duration)
                .getResultList();
        if (!registrantActivities.isEmpty() || registrantActivities.size() > 0) {
            return registrantActivities.get(0);
        } else {
            return null;
        }
    }

    public List<Registrant> getRegistrantActivities(RegistrantActivityType registrantActivityType, Duration duration) {
        List<RegistrantActivity> activitys = this.entityManager.createQuery("Select a from RegistrantActivity a where a.registrantActivityType=:registrantActivityType and a.duration=:duration")
                .setParameter("registrantActivityType", registrantActivityType)
                .setParameter("duration", duration)
                .getResultList();
        Set<Registrant> items = new HashSet<Registrant>();
        for (RegistrantActivity activity : activitys) {
            items.add(activity.getRegistrant());
        }
        return new ArrayList<Registrant>(items);
    }
}
