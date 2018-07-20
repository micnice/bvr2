package zw.co.micnice.web.pages;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import zw.co.micnice.web.config.CouncilSession;

public class LoginPage extends WebPage {

    public LoginPage() {
        add(new SignInForm("signInForm"));
        add(new FeedbackPanel("errorMessage"));

      
    }

    /**
     * Sign in form
     */
    public final class SignInForm extends Form<Void> {

        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";
        // El-cheapo model for form
        private final ValueMap properties = new ValueMap();

        /**
         * Constructor
         *
         * @param id id of the form component
         */
        public SignInForm(final String id) {
            super(id);
            
            add(new TextField<String>(USERNAME, new PropertyModel<String>(properties, USERNAME)).setRequired(true));
            add(new PasswordTextField(PASSWORD, new PropertyModel<String>(properties, PASSWORD)).setRequired(true));
            
        }

        /**
         * @see org.apache.wicket.markup.html.form.Form#onSubmit()
         */
        @Override
        public final void onSubmit() {

            // Get session info
            CouncilSession session = getMySession();

            // Sign the user in
            if (session.signIn(getUsername(), getPassword())) {
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            } else {

                // Get the error message from the properties file associated with the Component
                String errmsg = getString("loginError", null, "Unable to sign you in");

                // Register the error message with the feedback panel
                error(errmsg);
            }
        }

        /**
         * @return
         */
        private String getPassword() {
            return properties.getString(PASSWORD);
        }

        /**
         * @return
         */
        private String getUsername() {
            return properties.getString(USERNAME);
        }

        /**
         * @return
         */
        private CouncilSession getMySession() {
            return (CouncilSession) getSession();
        }
    }
}