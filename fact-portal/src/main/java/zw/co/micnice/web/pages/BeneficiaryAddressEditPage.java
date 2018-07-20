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
import zw.co.micnice.logic.domain.BeneficiaryAddress;
import zw.co.micnice.logic.service.AddressTypeService;
import zw.co.micnice.logic.service.BeneficiaryAddressService;
import zw.co.micnice.logic.utils.CouncilException;
import zw.co.micnice.web.utility.ErrorBehavior;

public class BeneficiaryAddressEditPage extends TemplatePage {

    @SpringBean
    private BeneficiaryAddressService beneficiaryAddressService;
    @SpringBean
    private AddressTypeService addressTypeService;

    public BeneficiaryAddressEditPage(final IModel<Beneficiary> beneficiaryModel) {

        CompoundPropertyModel<BeneficiaryAddress> model = new CompoundPropertyModel<BeneficiaryAddress>(
                new BeneficiaryAddressEditPage.LoadableDetachableBeneficiaryAddressServiceModel(
                beneficiaryModel.getObject()));

        setDefaultModel(model);

        Form<BeneficiaryAddress> form = new Form<BeneficiaryAddress>("form", (IModel<BeneficiaryAddress>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    getModelObject().setBeneficiary(beneficiaryModel.getObject());
                    beneficiaryAddressService.save(getModelObject());
                    setResponsePage(new BeneficiaryContactPage(getModelObject().getBeneficiary().getId()));
                } catch (CouncilException ex) {
                    error(ex.getMessage());

                }

            }
        };

        form.add(new DropDownChoice("addressType", addressTypeService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("addressDetail").setRequired(true).add(new ErrorBehavior()));
        form.add(new CheckBox("status"));
        form.add(new BookmarkablePageLink("btnCancel", HomePage.class));

//        add(new Label("beneficiary.beneficiaryName"));
        add(new FeedbackPanel("errorMessage"));
        add(form);


    }

    public BeneficiaryAddressEditPage(long id) {

        CompoundPropertyModel<BeneficiaryAddress> model = new CompoundPropertyModel<BeneficiaryAddress>(
                new BeneficiaryAddressEditPage.LoadableDetachableBeneficiaryAddressServiceModel(id));
        final Long beneficiaryId = model.getObject().getBeneficiary().getId();

        setDefaultModel(model);

        Form<BeneficiaryAddress> form = new Form<BeneficiaryAddress>("form", (IModel<BeneficiaryAddress>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    beneficiaryAddressService.save(getModelObject());
                    if (beneficiaryId != null) {
                        setResponsePage(new BeneficiaryContactPage(beneficiaryId));
                    }
                } catch (CouncilException ex) {
                    error(ex.getMessage());

                }

            }
        };

        form.add(new DropDownChoice("addressType", addressTypeService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("addressDetail").setRequired(true).add(new ErrorBehavior()));
        form.add(new CheckBox("status"));
        form.add(new BookmarkablePageLink("btnCancel", HomePage.class));

        add(new FeedbackPanel("errorMessage"));
        add(form);

    }

    private final class LoadableDetachableBeneficiaryAddressServiceModel extends LoadableDetachableModel<BeneficiaryAddress> {

        private Long id;
        private Beneficiary beneficiary;

        public LoadableDetachableBeneficiaryAddressServiceModel(Long id) {
            this.id = id;
        }

        public LoadableDetachableBeneficiaryAddressServiceModel(Beneficiary beneficiary) {
            this.beneficiary = beneficiary;
        }

        @Override
        protected BeneficiaryAddress load() {
            BeneficiaryAddress beneficiaryAddress;

            if (id == null) {
                beneficiaryAddress = new BeneficiaryAddress();
                beneficiaryAddress.setBeneficiary(beneficiary);
            } else {
                beneficiaryAddress = beneficiaryAddressService.get(id);
            }

            return beneficiaryAddress;
        }
    }
}
