package zw.co.micnice.web.pages;

import zw.co.micnice.web.pages.panels.BeneficiaryGradePanelList;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.web.util.DetachableBeneficiaryModel;

/**
 *
 * @author Michael Matiashe
 */
public class BeneficiaryGradePage extends TemplatePage {
    
    @SpringBean
    private BeneficiaryService beneficiaryService;
    
    public BeneficiaryGradePage(PageParameters parameters) {
        this(parameters.get("beneficiaryId").toLong());
    }

    public BeneficiaryGradePage(Long id) {
        
        final CompoundPropertyModel<Beneficiary> model = new CompoundPropertyModel<Beneficiary>(new DetachableBeneficiaryModel(id, beneficiaryService));
        
        setDefaultModel(model);
        
        add(new BeneficiaryGradePanelList("beneficiaryGradePanelList", model));

        add(new Link<Beneficiary>("next") {
            @Override
            public void onClick() {
                Beneficiary beneficiary = model.getObject();
              setResponsePage(new BeneficiaryViewPage(beneficiary.getId()));
            }
        });        
    }
}
