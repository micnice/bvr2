package zw.co.micnice.web.pages.admin;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;

/**
 *
 * @author Matiashe Michael
 */
public class AccountsParametersViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private AccountsParametersService accountsParametersService;

    public AccountsParametersViewPage() {
        CompoundPropertyModel<AccountsParameters> model = new CompoundPropertyModel<AccountsParameters>(new LoadableDetachableAccoutsParametersModel());
        setDefaultModel(model);
        add(new Link<AccountsParameters>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new AccountsParametersEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("invoiceTransactionType"));
        add(new Label("creditNoteTransactionType"));
        add(new Label("defaultPaymentMethod"));
        add(new BookmarkablePageLink("btnCancel", AdministerDatabasePage.class));
    }


      private final class LoadableDetachableAccoutsParametersModel extends LoadableDetachableModel<AccountsParameters> {

        @Override
        protected AccountsParameters load() {

            List<AccountsParameters> list = accountsParametersService.findAll();
            if (list.isEmpty()) {
                return new AccountsParameters();
            } else {
                return list.get(0);
            }

        }
    }
}
