package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;
import zw.co.micnice.web.models.PaymentMethodListModel;

/**
 *
 * @author Michael Matiashe
 */
public class AccountsParametersEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private AccountsParametersService accountsParametersService;
    @SpringBean
    private PaymentMethodService paymentMethodService;
    @SpringBean
    private TransactionTypeService transactionTypeService;

    public AccountsParametersEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<AccountsParameters>(new LoadableDetachableAccountsParametersModel(id)));
        Form<AccountsParameters> form=new Form<AccountsParameters>("form",(IModel<AccountsParameters>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                accountsParametersService.save(getModelObject());
                setResponsePage(new AccountsParametersViewPage());
            }
        };
        form.add(new DropDownChoice("invoiceTransactionType", transactionTypeService.findAll()));
        form.add(new DropDownChoice("creditNoteTransactionType", transactionTypeService.findAll()));
        form.add(new DropDownChoice("defaultPaymentMethod", new PaymentMethodListModel(paymentMethodService)));
        form.add(new BookmarkablePageLink("btnCancel", AccountsParametersViewPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachableAccountsParametersModel extends LoadableDetachableModel<AccountsParameters> {

        private Long id;

        public LoadableDetachableAccountsParametersModel(Long id) {
            this.id = id;
        }

        @Override
        protected AccountsParameters load() {
            if(id==null){
                return new AccountsParameters();
            }
            return accountsParametersService.get(id);
        }
    }
    
    
}
