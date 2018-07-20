/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao;

import java.util.List;
import zw.co.micnice.logic.domain.Duration;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantActivity;
import zw.co.micnice.logic.domain.RegistrantActivityType;

/**
 *
 * @author hitrac
 */
public interface RegistrantActivityDAO extends IGenericDAO<RegistrantActivity>{
    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant,RegistrantActivityType registrantActivityType);
    public RegistrantActivity getRegistrantActivityByDuration(Registrant registrant, Duration duration);
    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration);
    public RegistrantActivity getRegistrantActivity(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration);
    public List<Registrant> getRegistrantActivities(RegistrantActivityType registrantActivityType, Duration duration);
}
