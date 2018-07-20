/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages;

import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.service.BeneficiaryGradeService;
import zw.co.micnice.web.pages.panels.BeneficiaryGradeDataViewPanelList;

public class BeneficiaryGradeDataListPage extends TemplatePage{

    @SpringBean
    BeneficiaryGradeService beneficiaryGradeService;

    public BeneficiaryGradeDataListPage() {

        IModel<List<BeneficiaryGrade>> model = new LoadableDetachableBeneficiaryGradeListModel();
        add(new BeneficiaryGradeDataViewPanelList("companiesGradesList", model));

    }

    private class LoadableDetachableBeneficiaryGradeListModel extends LoadableDetachableModel<List<BeneficiaryGrade>> {


        @Override
        protected List<BeneficiaryGrade> load() {
            return beneficiaryGradeService.getSummedBeneficiaryGrades();
        }
    }
}
