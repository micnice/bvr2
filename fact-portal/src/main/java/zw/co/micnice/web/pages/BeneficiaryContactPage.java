package zw.co.micnice.web.pages;

import zw.co.micnice.web.pages.panels.BeneficiaryContactPanelList;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.web.pages.panels.BeneficiaryAddressPanelList;
import zw.co.micnice.web.util.DetachableBeneficiaryModel;


public class BeneficiaryContactPage extends TemplatePage {
    
    @SpringBean
    private BeneficiaryService beneficiaryService;
    
    public BeneficiaryContactPage(PageParameters parameters) {
        this(parameters.get("beneficiaryId").toLong());
    }

    public BeneficiaryContactPage(Long id) {
        
        final CompoundPropertyModel<Beneficiary> model = new CompoundPropertyModel<Beneficiary>(new DetachableBeneficiaryModel(id, beneficiaryService));
        
        setDefaultModel(model);
        
        add(new BeneficiaryContactPanelList("beneficiaryContactPanelList", model));
        add(new BeneficiaryAddressPanelList("beneficiaryAddressPanelList", model));

        add(new Link<Void>("next") {
            @Override
            public void onClick() {
              setResponsePage(new BeneficiaryGradePage(model.getObject().getId()));
            }
        });        
    }
}
