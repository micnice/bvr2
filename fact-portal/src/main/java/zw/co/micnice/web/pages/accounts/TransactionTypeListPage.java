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
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class TransactionTypeListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private TransactionTypeService transactionTypeService;

    public TransactionTypeListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new TransactionTypeEditPage(null));
            }
        });
        
         add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
        
        IModel<List<TransactionType>> model=new LoadableDetachableModel<List<TransactionType>>() {

            @Override
            protected List<TransactionType> load() {
               return transactionTypeService.findAll();
            }
        };
        
        PropertyListView<TransactionType> eachItem=new PropertyListView<TransactionType>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<TransactionType> item) {
                Link<TransactionType> viewLink=new Link<TransactionType>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new TransactionTypeViewPage(getModelObject().getId()));
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
