
package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Duration;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantActivity;
import zw.co.micnice.logic.domain.RegistrantActivityType;

/**
 *
 * @author  Kelvin Goredema
 */
public interface RegistrantActivityService extends IGenericService<RegistrantActivity> {
    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant,RegistrantActivityType registrantActivityType);
    public RegistrantActivity getRegistrantActivityByDuration(Registrant registrant, Duration duration);
    public List<RegistrantActivity> getRegistrantActivities(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration);
    public RegistrantActivity getRegistrantActivity(Registrant registrant, RegistrantActivityType registrantActivityType, Duration duration);
    public List<Registrant> getRegistrantActivities(RegistrantActivityType registrantActivityType, Duration duration);
}
