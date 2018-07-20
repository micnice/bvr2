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
import zw.co.micnice.logic.domain.BeneficiaryContact;
import zw.co.micnice.logic.service.BeneficiaryContactService;
import zw.co.micnice.web.pages.BeneficiaryContactEditPage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class BeneficiaryContactPanelList extends Panel {

    @SpringBean
    private BeneficiaryContactService beneficiaryContactService;
    
    public BeneficiaryContactPanelList(String id, final IModel<Beneficiary> beneficiaryModel) {
        super(id);

        add(new Link<Beneficiary>("beneficiaryContactLink") {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryContactEditPage(beneficiaryModel));
            }
        });

        add(new PropertyListView<BeneficiaryContact>("beneficiaryContactList", beneficiaryContactService.findByBeneficiary(beneficiaryModel.getObject())) {
            @Override
            protected void populateItem(ListItem<BeneficiaryContact> item) {
                Link<BeneficiaryContact> viewLink = new Link<BeneficiaryContact>("beneficiaryContactLink", item.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(new BeneficiaryContactEditPage(getModelObject().getId()));
                    }
                };

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(viewLink);
                item.add(new Label("contactType.name"));
                item.add(new Label("contactDetail"));
            }
        });

    }
    
}
