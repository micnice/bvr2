package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.ContactType;
import zw.co.micnice.logic.service.ContactTypeService;

/**
 *
 * @author Matiashe Michael
 */
public class ContactTypeViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private ContactTypeService contactTypeService;

    public ContactTypeViewPage(Long id) {
        CompoundPropertyModel<ContactType> model=new CompoundPropertyModel<ContactType>(new LoadableDetachableContactTypeModel(id));
        setDefaultModel(model);
        add(new Link<ContactType>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new ContactTypeEditPage(getModelObject().getId()));
            }
            
        });
        add(new BookmarkablePageLink("btnCancel", ContactTypeListPage.class));
        add(new Label("name"));
        add(new Label("description"));
    }


    private final class LoadableDetachableContactTypeModel extends LoadableDetachableModel<ContactType> {

        private Long id;

        public LoadableDetachableContactTypeModel(Long id) {
            this.id = id;
        }

        @Override
        protected ContactType load() {

            return contactTypeService.get(id);
        }
    }
}
