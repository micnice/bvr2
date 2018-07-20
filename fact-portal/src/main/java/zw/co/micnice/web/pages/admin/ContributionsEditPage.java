package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.Contribution;
import zw.co.micnice.logic.service.ContributionService;
import zw.co.micnice.web.pages.BeneficiaryListPage;
import zw.co.micnice.web.utility.ErrorBehavior;

public class ContributionsEditPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private ContributionService contributionService;

    public ContributionsEditPage(final Beneficiary beneficiary) {
        CompoundPropertyModel<Contribution> model = new CompoundPropertyModel<Contribution>(new ContributionsEditPage.LoadableDetachableBeneficiaryModel(null));
        model.getObject().setBeneficiary(beneficiary);
        setDefaultModel(model);
        Form<Contribution> form = new Form<Contribution>("form", (IModel<Contribution>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                Contribution contribution = getModelObject();
                contribution.setBeneficiary(beneficiary);
                contributionService.save(contribution);
                setResponsePage(new BeneficiaryListPage());
            }
        };

        form.add(new Label("beneficiary.firstName"));
        form.add(new TextField<String>("number").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("max"));
        form.add(new TextField<String>("start"));
        form.add(new TextField<String>("inc"));
        form.add(new TextField<String>("email"));
        add(form);
        add(new FeedbackPanel("errorMessage"));
         
    }

    private class LoadableDetachableBeneficiaryModel extends LoadableDetachableModel<Contribution> {

        private Long id;

        public LoadableDetachableBeneficiaryModel(Long id) {
            this.id = id;
        }

        @Override
        protected Contribution load() {
            if (id == null) {
                return new Contribution();
            }

            Contribution contribution = contributionService.get(id);
            return contribution;
        }
    }

}
