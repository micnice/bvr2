package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.GradeService;

/**
 *
 * @author charlesc
 */
public class GradeViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private GradeService gradeService;

    public GradeViewPage(PageParameters pageParameters) {
        this(pageParameters.get("gradeId").toLong());
    }
    public GradeViewPage(Long gradeID) {
        CompoundPropertyModel<Grade> model=new CompoundPropertyModel<Grade>(new LoadableDetachableGradeModel(gradeID));
        setDefaultModel(model);
        add(new Link<Grade>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new GradeEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("fullName"));
        add(new Label("shortName"));
        add(new Label("hourlyRate"));
        add(new Label("weeklyRate"));
        add(new Label("monthlyRate"));
        add(new BookmarkablePageLink("btnCancel", GradeListPage.class));
    }


    private final class LoadableDetachableGradeModel extends LoadableDetachableModel<Grade> {

        private Long id;

        public LoadableDetachableGradeModel(Long id) {
            this.id = id;
        }

        @Override
        protected Grade load() {

            return gradeService.get(id);
        }
    }
}
