package zw.co.micnice.web.config;

import java.util.Locale;
import java.util.Set;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.service.UserService;
import zw.co.micnice.logic.utils.Role;

public class CouncilSession extends AuthenticatedWebSession {

    @SpringBean
    private transient UserService userService;
    private User user;

    public CouncilSession(Request request) {
        super(request);
        this.setLocale(Locale.UK);
        Injector.get().inject(this);
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            //TODO : Investigate null pointer issue when user is not found
            User $user = this.userService.get(username, password);
            setUser($user);
            return user != null;

        } catch (NullPointerException ex) {
            return false;
        }
    }

    @Override
    public Roles getRoles() {
        Set<Role> userRoles = user.getRoles();

        String userRolesString[] = new String[userRoles.size()];
        int i = 0;

        for (Role role : user.getRoles()) {
            userRolesString[i++] = role.getRoleName();
        }

        Roles roles = new Roles(userRolesString);
        return roles;
    }

    public boolean hasAnyRole(Roles roles) {
        if (getUser() == null) {
            return false;
        }
        return this.getRoles().hasAnyRole(roles);
    }

    public boolean hasRole(String role) {
        if (getUser() == null) {
            return false;
        }
        return this.getRoles().hasRole(role);
    }

    public boolean hasAllRoles(Roles roles) {
        if (getUser() == null) {
            return false;
        }
        return this.getRoles().hasAllRoles(roles);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static CouncilSession get() {
        return (CouncilSession) Session.get();
    }
}
