package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Province;
import zw.co.micnice.logic.service.ProvinceService;

/**
 *
 * @author Michael Matiashe
 */
public class ProvinceViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private ProvinceService provinceService;

    public ProvinceViewPage(Long id) {
        CompoundPropertyModel<Province> model=new CompoundPropertyModel<Province>(new LoadableDetachableProvinceModel(id));
        setDefaultModel(model);
        add(new Link<Province>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new ProvinceEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("name"));
        add(new Label("description"));
        add(new Label("shortName"));
        add(new BookmarkablePageLink("btnCancel", ProvinceListPage.class));
    }


    private final class LoadableDetachableProvinceModel extends LoadableDetachableModel<Province> {

        private Long id;

        public LoadableDetachableProvinceModel(Long id) {
            this.id = id;
        }

        @Override
        protected Province load() {

            return provinceService.get(id);
        }
    }
}
