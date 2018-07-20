
package zw.co.micnice.web.pages;

import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Clive Gurure
 */
public class SignOutPage extends WebPage {

    public SignOutPage() {
        //TODO : End the current request cycle to prevent access of previous pages    
        getSession().invalidate();
        setResponsePage(LoginPage.class);
    }
}
