package zw.co.micnice.web.pages.accounts;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.Bank;
import zw.co.micnice.logic.service.accounts.BankService;
import zw.co.micnice.web.pages.admin.AdministerDatabasePage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

public class BankListPage extends IBankBookPage {

    @SpringBean
    private BankService bankService;

    public BankListPage() {
        add(new Link<Void>("addNewLink") {
            @Override
            public void onClick() {
                setResponsePage(new BankEditPage(null));
            }
        });
        
         add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });

        IModel<List<Bank>> model = new LoadableDetachableModel<List<Bank>>() {
            @Override
            protected List<Bank> load() {
                return bankService.findAll();
            }
        };

        PropertyListView<Bank> eachItem = new PropertyListView<Bank>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<Bank> item) {
                Link<Bank> viewLink = new Link<Bank>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(new BankViewPage(getModelObject().getId()));
                    }
                };
                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                viewLink.add(new Label("name"));
                item.add(new Label("accNumber"));
                item.add(new Label("branch"));
                item.add(new Label("bank"));
            }
        };

        add(eachItem);
    }
}
