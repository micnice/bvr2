/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages;

import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.web.pages.panels.BeneficiaryDataViewPanelList;

public class BeneficiaryListPage extends TemplatePage{

    @SpringBean
    BeneficiaryService beneficiaryService;

    public BeneficiaryListPage() {

        IModel<List<Beneficiary>> model = new LoadableDetachableBeneficiaryListModel();
        add(new BeneficiaryDataViewPanelList("companiesList", model));

    }

    private class LoadableDetachableBeneficiaryListModel extends LoadableDetachableModel<List<Beneficiary>> {


        @Override
        protected List<Beneficiary> load() {
            return beneficiaryService.getCompanies();
        }
    }
}
