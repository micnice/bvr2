package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import java.util.Arrays;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Effect;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;
import zw.co.micnice.web.models.PaymentMethodListModel;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class TransactionTypeEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private TransactionTypeService transactionTypeService;
    @SpringBean
    private AccountService accountService;
    @SpringBean
    private PaymentMethodService paymentMethodService;

    public TransactionTypeEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<TransactionType>(new LoadableDetachableTransactionTypeModel(id)));
        Form<TransactionType> form=new Form<TransactionType>("form",(IModel<TransactionType>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                transactionTypeService.save(getModelObject());
                setResponsePage(new TransactionTypeViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new DropDownChoice("effect", Arrays.asList(Effect.values())));
        form.add(new DropDownChoice("book", Arrays.asList(Book.values()), new BookChoiceRenderer()));
        form.add(new DropDownChoice("crLedger", accountService.getGeneralLedgerAccounts(), new AccountChoiceRenderer()));
        form.add(new DropDownChoice("drLedger", accountService.getGeneralLedgerAccounts(), new AccountChoiceRenderer()));
        form.add(new DropDownChoice("paymentMethod", new PaymentMethodListModel(paymentMethodService)));
        form.add(new BookmarkablePageLink("btnCancel", TransactionTypeListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
     private static class AccountChoiceRenderer implements IChoiceRenderer<Account> {

        public Object getDisplayValue(Account account) {
            return account.getName();
        }

        public String getIdValue(Account account, int index) {
            return account.getId().toString();
        }
    }
    
    private class BookChoiceRenderer implements IChoiceRenderer<Book>{

        public Object getDisplayValue(Book book) {
           return book.getName();
        }

        public String getIdValue(Book book, int index) {
           return book.toString();
        }
        
    }
    
    private final class LoadableDetachableTransactionTypeModel extends LoadableDetachableModel<TransactionType> {

        private Long id;

        public LoadableDetachableTransactionTypeModel(Long id) {
            this.id = id;
        }

        @Override
        protected TransactionType load() {
            if(id==null){
                return new TransactionType();
            }
            return transactionTypeService.get(id);
        }
    }
    
    
}
