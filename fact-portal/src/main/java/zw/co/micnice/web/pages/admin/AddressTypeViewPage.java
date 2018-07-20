package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.AddressType;
import zw.co.micnice.logic.service.AddressTypeService;

/**
 *
 * @author Michael Matiashe
 */
public class AddressTypeViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private AddressTypeService addressTypeService;

    public AddressTypeViewPage(Long id) {
        CompoundPropertyModel<AddressType> model=new CompoundPropertyModel<AddressType>(new LoadableDetachableAddressTypeModel(id));
        setDefaultModel(model);
        add(new Link<AddressType>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new AddressTypeEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("name"));
        add(new Label("description"));
        add(new BookmarkablePageLink("btnCancel", AddressTypeListPage.class));
    }


    private final class LoadableDetachableAddressTypeModel extends LoadableDetachableModel<AddressType> {

        private Long id;

        public LoadableDetachableAddressTypeModel(Long id) {
            this.id = id;
        }

        @Override
        protected AddressType load() {

            return addressTypeService.get(id);
        }
    }
}
