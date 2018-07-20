package zw.co.micnice.web.pages.admin;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.GradeService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;


public class GradeListPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private GradeService gradeService;

    public GradeListPage() {
        add(new Link<Void>("addNewLink") {

            @Override
            public void onClick() {
               setResponsePage(new GradeEditPage(null));
            }
        });
        
        add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new AdministerDatabasePage());
            }
        });
        
        IModel<List<Grade>> model=new LoadableDetachableModel<List<Grade>>() {

            @Override
            protected List<Grade> load() {
               return gradeService.findAll();
            }
        };
        
        PropertyListView<Grade> eachItem=new PropertyListView<Grade>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<Grade> item) {
                Link<Grade> viewLink=new Link<Grade>("viewLink", item.getModel()) {
                    @Override
                    public void onClick() {
                       setResponsePage(new GradeViewPage(getModelObject().getId()));
                    }
                };
                if(item.getIndex()%2==0){
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                viewLink.add(new Label("fullName"));
                item.add(new Label("shortName"));
                item.add(new Label("hourlyRate"));
                item.add(new Label("weeklyRate"));
                item.add(new Label("monthlyRate"));
            }
        };
        
        add(eachItem);
    }

    
    
}
