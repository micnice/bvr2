/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.extensions.yui.calendar.DatePicker;
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
import zw.co.micnice.logic.domain.Gender;
import zw.co.micnice.logic.domain.LevelOfEducation;
import zw.co.micnice.logic.domain.MaritalStatus;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.DistrictService;
import zw.co.micnice.logic.service.MaritalStatusService;
import zw.co.micnice.logic.service.ProvinceService;
import zw.co.micnice.web.config.CouncilSession;
import zw.co.micnice.web.models.ProvinceListModel;
import zw.co.micnice.web.utility.DatePickerUtil;
import zw.co.micnice.web.utility.ErrorBehavior;

public class BeneficiaryEditPage extends TemplatePage {

    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    private MaritalStatusService maritalStatusService;

    public BeneficiaryEditPage() {
        this(null);
    }

    public BeneficiaryEditPage(Long id) {
        CompoundPropertyModel<Beneficiary> model = new CompoundPropertyModel<Beneficiary>(new BeneficiaryEditPage.LoadableDetachableBeneficiaryModel(id));
        setDefaultModel(model);

        Form<Beneficiary> form = new Form<Beneficiary>("beneficiaryForm", (IModel<Beneficiary>) getDefaultModel()) {
            @Override
            protected void onSubmit() {
                Beneficiary beneficiary = getModelObject();
                beneficiary.setGender(Gender.FEMALE);
                beneficiary.setIdentifiedBy(CouncilSession.get().getUser());
                beneficiary = beneficiaryService.save(beneficiary);
                setResponsePage(new BeneficiaryViewPage(beneficiary.getId()));
            }
        };

        form.add(new TextField<String>("firstName").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("middleName"));
        form.add(new TextField<String>("lastName").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("idNumber").setRequired(true).add(new ErrorBehavior()));
        //form.add(new DropDownChoice<MaritalStatus>("maritalStatus", maritalStatusService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new DropDownChoice<LevelOfEducation>("levelOfEducation", Arrays.asList(LevelOfEducation.values())).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<Date>("dateOfBirth").setRequired(true).add(new DatePicker()));
        form.add(new TextField<Date>("lmp").setRequired(true).add(new DatePicker()));
        form.add(new TextField<Date>("dateIdentified").setRequired(true).add(new DatePicker()));
        form.add(new TextField<Number>("parity").setRequired(true));

        form.add(new BookmarkablePageLink("btnCancel", HomePage.class));

        add(form);
        //TODO : Add all feedback panels to forms, because they appear funny if not
        add(new FeedbackPanel("errorMessage"));

    }

    private class LoadableDetachableBeneficiaryModel extends LoadableDetachableModel<Beneficiary> {

        private Long id;

        public LoadableDetachableBeneficiaryModel(Long id) {
            this.id = id;
        }

        @Override
        protected Beneficiary load() {
            if (id == null) {
                return new Beneficiary();
            }

            Beneficiary beneficiary = beneficiaryService.get(id);
            return beneficiary;
        }
    }
}
