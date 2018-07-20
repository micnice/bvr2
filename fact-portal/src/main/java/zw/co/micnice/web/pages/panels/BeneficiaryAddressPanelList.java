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
import zw.co.micnice.logic.domain.BeneficiaryAddress;
import zw.co.micnice.logic.service.BeneficiaryAddressService;
import zw.co.micnice.web.pages.BeneficiaryAddressEditPage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class BeneficiaryAddressPanelList extends Panel {

    @SpringBean
    private BeneficiaryAddressService beneficiaryAddressService;
    
    public BeneficiaryAddressPanelList(String id, final IModel<Beneficiary> beneficiaryModel) {
        super(id);

        add(new Link<Beneficiary>("beneficiaryAddressLink") {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryAddressEditPage(beneficiaryModel));
            }
        });

        add(new PropertyListView<BeneficiaryAddress>("beneficiaryAddressList", beneficiaryAddressService.findByBeneficiary(beneficiaryModel.getObject())) {
            @Override
            protected void populateItem(ListItem<BeneficiaryAddress> item) {
                Link<BeneficiaryAddress> viewLink = new Link<BeneficiaryAddress>("beneficiaryAddressLink", item.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(new BeneficiaryAddressEditPage(getModelObject().getId()));
                    }
                };

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                item.add(new Label("addressType.name"));
                item.add(new Label("addressDetail"));
            }
        });

    }
    
}
