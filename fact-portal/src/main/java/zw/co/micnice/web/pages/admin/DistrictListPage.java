package zw.co.micnice.web.pages.admin;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.District;
import zw.co.micnice.logic.service.DistrictService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class DistrictListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private DistrictService districtService;

    public DistrictListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new DistrictEditPage(null));
            }
        });
        
        add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
        
        IModel<List<District>> model=new LoadableDetachableModel<List<District>>() {

            @Override
            protected List<District> load() {
               return districtService.findAll();
            }
        };
        
        PropertyListView<District> eachItem=new PropertyListView<District>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<District> item) {
                Link<District> viewLink=new Link<District>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new DistrictViewPage(getModelObject().getId()));
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
