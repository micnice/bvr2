package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.ContactType;
import zw.co.micnice.logic.service.ContactTypeService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Matiashe Michael
 */
public class ContactTypeEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private ContactTypeService contactTypeService;

    public ContactTypeEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<ContactType>(new LoadableDetachableContactTypeModel(id)));
        Form<ContactType> form=new Form<ContactType>("form",(IModel<ContactType>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                contactTypeService.save(getModelObject());
                setResponsePage(new ContactTypeViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new TextField<String>("description").add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("btnCancel", ContactTypeListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachableContactTypeModel extends LoadableDetachableModel<ContactType> {

        private Long id;

        public LoadableDetachableContactTypeModel(Long id) {
            this.id = id;
        }

        @Override
        protected ContactType load() {
            if(id==null){
                return new ContactType();
            }
            return contactTypeService.get(id);
        }
    }
    
    
}
