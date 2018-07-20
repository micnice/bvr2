package zw.co.micnice.web.pages.admin;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.AddressType;
import zw.co.micnice.logic.service.AddressTypeService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class AddressTypeListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private AddressTypeService addressTypeService;

    public AddressTypeListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new AddressTypeEditPage(null));
            }
        });
        
        add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
        
        IModel<List<AddressType>> model=new LoadableDetachableModel<List<AddressType>>() {

            @Override
            protected List<AddressType> load() {
               return addressTypeService.findAll();
            }
        };
        
        PropertyListView<AddressType> eachItem=new PropertyListView<AddressType>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<AddressType> item) {
                Link<AddressType> viewLink=new Link<AddressType>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new AddressTypeViewPage(getModelObject().getId()));
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
