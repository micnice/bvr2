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
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.GradeService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Charles Chigoriwa
 */
public class GradeEditPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private GradeService gradeService;

    public GradeEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<Grade>(new LoadableDetachableGradeModel(id)));
        Form<Grade> form = new Form<Grade>("form", (IModel<Grade>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                gradeService.save(getModelObject());
                setResponsePage(new GradeViewPage(getModelObject().getId()));
            }
        };

        form.add(new TextField<String>("fullName").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("shortName").add(new ErrorBehavior()));
        form.add(new TextField<String>("hourlyRate").add(new ErrorBehavior()));
        form.add(new TextField<String>("weeklyRate").add(new ErrorBehavior()));
        form.add(new TextField<String>("monthlyRate").add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("btnCancel", GradeListPage.class));
        form.add(new DropDownChoice("gradeCategory", gradeService.getGradeCategories()));

        add(form);
        add(new FeedbackPanel("errorMessage"));
    }

    private final class LoadableDetachableGradeModel extends LoadableDetachableModel<Grade> {

        private Long id;

        public LoadableDetachableGradeModel(Long id) {
            this.id = id;
        }

        @Override
        protected Grade load() {
            if (id == null) {
                return new Grade();
            }
            return gradeService.get(id);
        }
    }
}
