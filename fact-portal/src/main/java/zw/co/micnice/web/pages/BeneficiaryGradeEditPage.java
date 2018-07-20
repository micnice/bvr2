/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.service.BeneficiaryGradeService;
import zw.co.micnice.logic.service.GradeService;
import zw.co.micnice.logic.utils.CouncilException;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class BeneficiaryGradeEditPage extends TemplatePage {

    @SpringBean
    private BeneficiaryGradeService beneficiaryGradeService;
    @SpringBean
    private GradeService gradeService;

    public BeneficiaryGradeEditPage(final IModel<Beneficiary> beneficiaryModel) {

        CompoundPropertyModel<BeneficiaryGrade> model = new CompoundPropertyModel<BeneficiaryGrade>(
                new BeneficiaryGradeEditPage.LoadableDetachableBeneficiaryGradeServiceModel(
                beneficiaryModel.getObject()));

        setDefaultModel(model);

        
        Form<BeneficiaryGrade> form = new Form<BeneficiaryGrade>("form", (IModel<BeneficiaryGrade>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    getModelObject().setBeneficiary(beneficiaryModel.getObject());
                    beneficiaryGradeService.save(getModelObject());
                    setResponsePage(new BeneficiaryGradePage(getModelObject().getBeneficiary().getId()));
                } catch (CouncilException ex) {
                    error(ex.getMessage());

                }

            }
        };
        
        PageParameters pageParameters = new PageParameters();
        pageParameters.add("beneficiaryId", model.getObject().getBeneficiary().getId());

        form.add(new DropDownChoice("grade", gradeService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("numberOfEmployees").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("totalBasic").setRequired(true).add(new ErrorBehavior()));
        form.add(new CheckBox("status"));
        form.add(new BookmarkablePageLink("btnCancel", BeneficiaryGradePage.class,pageParameters));

      
        add(new FeedbackPanel("errorMessage"));
        add(new Label("beneficiary.beneficiaryName"));
        add(form);


    }

    public BeneficiaryGradeEditPage(long id) {

        CompoundPropertyModel<BeneficiaryGrade> model = new CompoundPropertyModel<BeneficiaryGrade>(
                new BeneficiaryGradeEditPage.LoadableDetachableBeneficiaryGradeServiceModel(id));
        Long rId = null;
        rId = model.getObject().getBeneficiary().getId();

        final Long beneficiaryId = rId;
        PageParameters pageParameters = new PageParameters();
        pageParameters.add("beneficiaryId", beneficiaryId);
        
        setDefaultModel(model);

        Form<BeneficiaryGrade> form = new Form<BeneficiaryGrade>("form", (IModel<BeneficiaryGrade>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    beneficiaryGradeService.save(getModelObject());
                    if (beneficiaryId != null) {
                        setResponsePage(new BeneficiaryGradePage(beneficiaryId));
                    }
                } catch (CouncilException ex) {
                    error(ex.getMessage());

                }

            }
        };

        form.add(new DropDownChoice("grade", gradeService.findAll()).setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("numberOfEmployees").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<Double>("totalBasicPay").setRequired(true).add(new ErrorBehavior()));
        form.add(new CheckBox("status"));
        form.add(new BookmarkablePageLink("btnCancel", BeneficiaryGradePage.class,pageParameters));
        add(new Label("beneficiary.beneficiaryname"));
        add(new FeedbackPanel("errorMessage"));
        add(form);

//        if (beneficiaryId != null) {
//            add(new Label("beneficiary.beneficiaryName"));
//            
//        }
     

    }

    private final class LoadableDetachableBeneficiaryGradeServiceModel extends LoadableDetachableModel<BeneficiaryGrade> {

        private Long id;
        private Beneficiary beneficiary;

        public LoadableDetachableBeneficiaryGradeServiceModel(Long id) {
            this.id = id;
        }

        public LoadableDetachableBeneficiaryGradeServiceModel(Beneficiary beneficiary) {
            this.beneficiary = beneficiary;
        }

        @Override
        protected BeneficiaryGrade load() {
            BeneficiaryGrade beneficiaryGrade = null;

            if (id == null) {
                beneficiaryGrade = new BeneficiaryGrade();
                beneficiaryGrade.setBeneficiary(beneficiary);
            } else {
                beneficiaryGrade = beneficiaryGradeService.get(id);
            }

            return beneficiaryGrade;
        }
    }
}
