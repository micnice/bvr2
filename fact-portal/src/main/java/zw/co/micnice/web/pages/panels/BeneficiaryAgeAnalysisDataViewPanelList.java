
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
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.web.pages.BeneficiaryViewPage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Michael
 */
public class BeneficiaryAgeAnalysisDataViewPanelList extends Panel {
    @SpringBean
    private DebtComponentService debtComponentService;

    public BeneficiaryAgeAnalysisDataViewPanelList(String id, IModel<List<Beneficiary>> model) {
        super(id);

        PageableListView<Beneficiary> eachItem = new PageableListView<Beneficiary>("eachItem", model, 20) {
            @Override
            protected void populateItem(ListItem<Beneficiary> item) {

                PageParameters params = new PageParameters();
                params.add("beneficiaryId", item.getModelObject().getId());
                item.add(new BookmarkablePageLink("beneficiaryViewPage", BeneficiaryViewPage.class, params).add(new Label("beneficiaryName", item.getModelObject().getFirstName())));

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
//                item.add(new Label("beneficiaryNumber", item.getModelObject().getProvince().getShortName().concat(" "+item.getModelObject().getId().toString())));
//                item.add(new Label("threeOrMore", debtComponentService.getTotalDues(item.getModelObject().getAccount(), Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE)));
//                item.add(new Label("twoMonths", debtComponentService.getTotalDues(item.getModelObject().getAccount(), Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE)));
//                item.add(new Label("oneMonth", debtComponentService.getTotalDues(item.getModelObject().getAccount(), Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE)));
//                item.add(new Label("current",debtComponentService.getTotalDues(item.getModelObject().getAccount(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE)));
//                item.add(new Label("total", debtComponentService.getTotalDues(item.getModelObject().getAccount(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE)));

            }
        };

        //add(new PagingNavigator("navigator", eachItem));
        add(eachItem);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
