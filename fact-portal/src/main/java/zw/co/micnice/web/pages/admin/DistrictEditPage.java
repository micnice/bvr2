package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.District;
import zw.co.micnice.logic.service.DistrictService;
import zw.co.micnice.logic.service.ProvinceService;
import zw.co.micnice.web.models.ProvinceListModel;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class DistrictEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private DistrictService districtService;
    @SpringBean
    private ProvinceService provinceService;

    public DistrictEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<District>(new LoadableDetachableDistrictModel(id)));
        Form<District> form=new Form<District>("form",(IModel<District>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                districtService.save(getModelObject());
                setResponsePage(new DistrictViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new TextField<String>("description").add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("btnCancel", DistrictListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachableDistrictModel extends LoadableDetachableModel<District> {

        private Long id;

        public LoadableDetachableDistrictModel(Long id) {
            this.id = id;
        }

        @Override
        protected District load() {
            if(id==null){
                return new District();
            }
            return districtService.get(id);
        }
    }
    
    
}
