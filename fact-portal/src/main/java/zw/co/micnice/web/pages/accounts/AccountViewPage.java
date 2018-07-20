package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.service.accounts.AccountService;

/**
 *
 * @author Matiashe Michael
 */
public class AccountViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private AccountService accountService;

    public AccountViewPage(Long id) {
        CompoundPropertyModel<Account> model=new CompoundPropertyModel<Account>(new LoadableDetachableAccountModel(id));
        setDefaultModel(model);
        add(new Link<Account>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new AccountEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("name"));
        add(new Label("accountType"));
        add(new BookmarkablePageLink("btnCancel", AccountListPage.class));
    }


    private final class LoadableDetachableAccountModel extends LoadableDetachableModel<Account> {

        private Long id;

        public LoadableDetachableAccountModel(Long id) {
            this.id = id;
        }

        @Override
        protected Account load() {

            return accountService.get(id);
        }
    }
}
