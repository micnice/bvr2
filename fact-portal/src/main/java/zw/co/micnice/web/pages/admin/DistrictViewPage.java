package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.District;
import zw.co.micnice.logic.service.DistrictService;

/**
 *
 * @author Michael Matiashe
 */
public class DistrictViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private DistrictService districtService;

    public DistrictViewPage(Long id) {
        CompoundPropertyModel<District> model=new CompoundPropertyModel<District>(new LoadableDetachableDistrictModel(id));
        setDefaultModel(model);
        add(new Link<District>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new DistrictEditPage(getModelObject().getId()));
            }
        });
        add(new Label("name"));
        add(new Label("description"));
        add(new BookmarkablePageLink("btnCancel", DistrictListPage.class));
    }


    private final class LoadableDetachableDistrictModel extends LoadableDetachableModel<District> {

        private Long id;

        public LoadableDetachableDistrictModel(Long id) {
            this.id = id;
        }

        @Override
        protected District load() {

            return districtService.get(id);
        }
    }
}
