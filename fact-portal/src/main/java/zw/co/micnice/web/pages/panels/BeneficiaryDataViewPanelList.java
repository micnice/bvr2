
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages.panels;

//~--- non-JDK imports --------------------------------------------------------
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


//~--- JDK imports ------------------------------------------------------------

import java.util.List;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.web.pages.BeneficiaryEditPage;
import zw.co.micnice.web.pages.BeneficiaryViewPage;
import zw.co.micnice.web.pages.admin.ContributionsEditPage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Michael
 */
public class BeneficiaryDataViewPanelList extends Panel {

    public BeneficiaryDataViewPanelList(String id, IModel<List<Beneficiary>> model) {
        super(id);

        PageableListView<Beneficiary> eachItem = new PageableListView<Beneficiary>("eachItem", model, 20) {
            @Override
            protected void populateItem(final ListItem<Beneficiary> item) {

                PageParameters params = new PageParameters();
                params.add("beneficiaryId", item.getModelObject().getId());
                item.add(new BookmarkablePageLink("beneficiaryViewPage", BeneficiaryViewPage.class, params).add(new Label("firstName", item.getModelObject().getFirstName())));

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(new Label("firstName", item.getModelObject().getFirstName()));
                item.add(new Label("LastName", item.getModelObject().getLastName()));
                item.add(new Link("idNumber") {
                    
                    @Override
                    public void onClick() {
                        setResponsePage(new BeneficiaryEditPage(item.getModelObject().getId()));
                    }
                });
                
            }
        };

        //add(new PagingNavigator("navigator", eachItem));
        add(eachItem);
    }
    
}


//~ Formatted by Jindent --- http://www.jindent.com
