package zw.co.micnice.web.pages.admin;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Province;
import zw.co.micnice.logic.service.ProvinceService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class ProvinceListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private ProvinceService provinceService;

    public ProvinceListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new ProvinceEditPage(null));
            }
        });
        
        add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
        
        IModel<List<Province>> model=new LoadableDetachableModel<List<Province>>() {

            @Override
            protected List<Province> load() {
               return provinceService.findAll();
            }
        };
        
        PropertyListView<Province> eachItem=new PropertyListView<Province>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<Province> item) {
                Link<Province> viewLink=new Link<Province>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new ProvinceViewPage(getModelObject().getId()));
                    }
                };
                if(item.getIndex()%2==0){
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                viewLink.add(new Label("name"));
                item.add(new Label("shortName"));
            }
        };
        
        add(eachItem);
    }

    
    
}
