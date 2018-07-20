package zw.co.micnice.web.security;


import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import zw.co.micnice.web.config.CouncilSession;

public class UserRolesAuthorizer implements IRoleCheckingStrategy {
    public boolean hasAnyRole(Roles roles) {
        CouncilSession authSession = (CouncilSession) Session.get();

        return authSession.hasAnyRole(roles);
    }
}
