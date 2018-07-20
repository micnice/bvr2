package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Province;
import zw.co.micnice.logic.service.ProvinceService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class ProvinceEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private ProvinceService provinceService;

    public ProvinceEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<Province>(new LoadableDetachableProvinceModel(id)));
        Form<Province> form=new Form<Province>("form",(IModel<Province>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                provinceService.save(getModelObject());
                setResponsePage(new ProvinceViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new TextField<String>("description").add(new ErrorBehavior()));
        form.add(new TextField<String>("shortName").add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("btnCancel", ProvinceListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachableProvinceModel extends LoadableDetachableModel<Province> {

        private Long id;

        public LoadableDetachableProvinceModel(Long id) {
            this.id = id;
        }

        @Override
        protected Province load() {
            if(id==null){
                return new Province();
            }
            return provinceService.get(id);
        }
    }
    
    
}
