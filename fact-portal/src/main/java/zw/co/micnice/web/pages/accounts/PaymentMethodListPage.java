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
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class PaymentMethodListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private PaymentMethodService paymentMethodService;

    public PaymentMethodListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new PaymentMethodEditPage(null));
            }
        });
        
         add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
        
        IModel<List<PaymentMethod>> model=new LoadableDetachableModel<List<PaymentMethod>>() {

            @Override
            protected List<PaymentMethod> load() {
               return paymentMethodService.findAll();
            }
        };
        
        PropertyListView<PaymentMethod> eachItem=new PropertyListView<PaymentMethod>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<PaymentMethod> item) {
                Link<PaymentMethod> viewLink=new Link<PaymentMethod>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new PaymentMethodViewPage(getModelObject().getId()));
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
