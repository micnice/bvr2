/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.service.BeneficiaryGradeService;
import zw.co.micnice.web.pages.BeneficiaryGradeEditPage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class BeneficiaryGradePanelList extends Panel {

    @SpringBean
    private BeneficiaryGradeService beneficiaryGradeService;

    public BeneficiaryGradePanelList(String id, final IModel<Beneficiary> beneficiaryModel) {
        super(id);

        add(new Link<Beneficiary>("beneficiaryGradeLink") {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryGradeEditPage(beneficiaryModel));
            }
        });

        add(new PropertyListView<BeneficiaryGrade>("beneficiaryGradeList", beneficiaryGradeService.getActiveGrades(beneficiaryModel.getObject())) {
            @Override
            protected void populateItem(ListItem<BeneficiaryGrade> item) {
                Link<BeneficiaryGrade> viewLink = new Link<BeneficiaryGrade>("beneficiaryGradeLink", item.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(new BeneficiaryGradeEditPage(getModelObject().getId()));
                    }
                };

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                item.add(new Label("grade.fullName"));
                item.add(new Label("numberOfEmployees"));
            }
        });

    }
    
}
