package zw.co.micnice.web.config;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import zw.co.micnice.logic.utils.Role;
import zw.co.micnice.web.pages.HomePage;
import zw.co.micnice.web.pages.LoginPage;
import zw.co.micnice.web.security.UserRolesAuthorizer;
import static zw.co.micnice.web.utility.GeneralUtils.*;

public class CouncilApplication extends AuthenticatedWebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        mountPage("home", HomePage.class);

        getSecuritySettings().setAuthorizationStrategy(new RoleAuthorizationStrategy(new UserRolesAuthorizer()));
        try {
            MetaDataRoleAuthorizationStrategy.authorize(HomePage.class, getRoles(Role.values()));
        } catch (Exception ex) {

            System.out.println("Error occured while attempting to authorize the Home page");

        }
//        MetaDataRoleAuthorizationStrategy.authorize(AdministerDatabasePage.class, getRoles(SYSTEM_ADMINISTRATOR));
//        MetaDataRoleAuthorizationStrategy.authorize(AccountingPage.class, getRoles(SYSTEM_ADMINISTRATOR, ACCOUNTS_OFFICER, REGISTRAR));
        this.getMarkupSettings().setStripWicketTags(true); //IMPORTANT!
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        System.out.println("Getting the session");
        return CouncilSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        System.out.println("Getting the sign in page class");
        return LoginPage.class;
    }
}
