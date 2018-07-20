package zw.co.micnice.web.pages.admin;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.AccountsParameters;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

public class AccountsParametersListPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private AccountsParametersService accountsParametersService;

    public AccountsParametersListPage() {
        add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });

        IModel<List<AccountsParameters>> model = new LoadableDetachableModel<List<AccountsParameters>>() {
            @Override
            protected List<AccountsParameters> load() {
                return accountsParametersService.findAll();
            }
        };

        PropertyListView<AccountsParameters> eachItem = new PropertyListView<AccountsParameters>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<AccountsParameters> item) {
                Link<AccountsParameters> viewLink = new Link<AccountsParameters>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(new AccountsParametersViewPage());
                    }
                };
                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                viewLink.add(new Label("name"));
            }
        };

        add(eachItem);
    }
}
