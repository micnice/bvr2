package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import java.util.Arrays;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.AccountType;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class AccountEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private AccountService accountService;

    public AccountEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<Account>(new LoadableDetachableAccountModel(id)));
        Form<Account> form=new Form<Account>("form",(IModel<Account>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                accountService.save(getModelObject());
                setResponsePage(new AccountViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new DropDownChoice("accountType", Arrays.asList(AccountType.values())));
        form.add(new BookmarkablePageLink("btnCancel", AccountListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachableAccountModel extends LoadableDetachableModel<Account> {

        private Long id;

        public LoadableDetachableAccountModel(Long id) {
            this.id = id;
        }

        @Override
        protected Account load() {
            if(id==null){
                return new Account();
            }
            return accountService.get(id);
        }
    }
    
    
}
