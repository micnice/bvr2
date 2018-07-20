package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.AddressType;
import zw.co.micnice.logic.service.AddressTypeService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class AddressTypeEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private AddressTypeService addressTypeService;

    public AddressTypeEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<AddressType>(new LoadableDetachableAddressTypeModel(id)));
        Form<AddressType> form=new Form<AddressType>("form",(IModel<AddressType>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                addressTypeService.save(getModelObject());
                setResponsePage(new AddressTypeViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new TextField<String>("description").add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("btnCancel", AddressTypeListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachableAddressTypeModel extends LoadableDetachableModel<AddressType> {

        private Long id;

        public LoadableDetachableAddressTypeModel(Long id) {
            this.id = id;
        }

        @Override
        protected AddressType load() {
            if(id==null){
                return new AddressType();
            }
            return addressTypeService.get(id);
        }
    }
    
    
}
