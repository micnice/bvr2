package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.AdministerDatabasePage;
import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class AccountListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private AccountService accountService;

    public AccountListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new AccountEditPage(null));
            }
        });
        
         add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
         
        IModel<List<Account>> model=new LoadableDetachableModel<List<Account>>() {

            @Override
            protected List<Account> load() {
               return accountService.getGeneralLedgerAccounts();
            }
        };
        
        PropertyListView<Account> eachItem=new PropertyListView<Account>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<Account> item) {
                Link<Account> viewLink=new Link<Account>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new AccountViewPage(getModelObject().getId()));
                    }
                };
                if(item.getIndex()%2==0){
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                viewLink.add(new Label("name"));
            }
        };
        
        add(eachItem);
    }

    
    
}
