/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryContact;
import zw.co.micnice.logic.service.BeneficiaryContactService;
import zw.co.micnice.logic.service.ContactTypeService;
import zw.co.micnice.logic.utils.CouncilException;
import zw.co.micnice.web.utility.ErrorBehavior;

public class BeneficiaryContactEditPage extends TemplatePage {

    @SpringBean
    private BeneficiaryContactService beneficiaryContactService;
    @SpringBean
    private ContactTypeService contactTypeService;

    public BeneficiaryContactEditPage(final IModel<Beneficiary> beneficiaryModel) {

        CompoundPropertyModel<BeneficiaryContact> model = new CompoundPropertyModel<BeneficiaryContact>(
                new BeneficiaryContactEditPage.LoadableDetachableBeneficiaryContactServiceModel(
                beneficiaryModel.getObject()));

        setDefaultModel(model);

        Form<BeneficiaryContact> form = new Form<BeneficiaryContact>("form", (IModel<BeneficiaryContact>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    getModelObject().setBeneficiary(beneficiaryModel.getObject());
                    beneficiaryContactService.save(getModelObject());
                    setResponsePage(new BeneficiaryContactPage(getModelObject().getBeneficiary().getId()));
                } catch (CouncilException ex) {
                    error(ex.getMessage());

                }

            }
        };

        form.add(new DropDownChoice("contactType", contactTypeService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("contactDetail").setRequired(true).add(new ErrorBehavior()));
        form.add(new CheckBox("status"));
        form.add(new BookmarkablePageLink("btnCancel", HomePage.class));

//        add(new Label("beneficiary.beneficiaryName"));
        add(new FeedbackPanel("errorMessage"));
        add(form);


    }

    public BeneficiaryContactEditPage(long id) {

        CompoundPropertyModel<BeneficiaryContact> model = new CompoundPropertyModel<BeneficiaryContact>(
                new BeneficiaryContactEditPage.LoadableDetachableBeneficiaryContactServiceModel(id));
        final Long beneficiaryId = model.getObject().getBeneficiary().getId();

        setDefaultModel(model);

        Form<BeneficiaryContact> form = new Form<BeneficiaryContact>("form", (IModel<BeneficiaryContact>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    beneficiaryContactService.save(getModelObject());
                    if (beneficiaryId != null) {
                        setResponsePage(new BeneficiaryContactPage(beneficiaryId));
                    }
                } catch (CouncilException ex) {
                    error(ex.getMessage());

                }

            }
        };

        form.add(new DropDownChoice("contactType", contactTypeService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("contactDetail").setRequired(true).add(new ErrorBehavior()));
        form.add(new CheckBox("status"));
        form.add(new BookmarkablePageLink("btnCancel", HomePage.class));

        add(new FeedbackPanel("errorMessage"));
        add(form);

    }

    private final class LoadableDetachableBeneficiaryContactServiceModel extends LoadableDetachableModel<BeneficiaryContact> {

        private Long id;
        private Beneficiary beneficiary;

        public LoadableDetachableBeneficiaryContactServiceModel(Long id) {
            this.id = id;
        }

        public LoadableDetachableBeneficiaryContactServiceModel(Beneficiary beneficiary) {
            this.beneficiary = beneficiary;
        }

        @Override
        protected BeneficiaryContact load() {
            BeneficiaryContact beneficiaryContact;

            if (id == null) {
                beneficiaryContact = new BeneficiaryContact();
                beneficiaryContact.setBeneficiary(beneficiary);
            } else {
                beneficiaryContact = beneficiaryContactService.get(id);
            }

            return beneficiaryContact;
        }
    }
}
