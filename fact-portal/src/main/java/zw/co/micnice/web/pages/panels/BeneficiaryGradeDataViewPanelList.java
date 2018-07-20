
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages.panels;

import java.math.BigDecimal;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.service.ContributionService;
import zw.co.micnice.web.pages.BeneficiaryViewPage;
import zw.co.micnice.web.pages.admin.GradeViewPage;
import zw.co.micnice.web.utility.EvenTableRowBehavior;
import zw.co.micnice.web.utility.GeneralUtils;

public class BeneficiaryGradeDataViewPanelList extends Panel {

    @SpringBean
    private ContributionService contributionService;
 
    public BeneficiaryGradeDataViewPanelList(String id, IModel<List<BeneficiaryGrade>> model) {
        super(id);

        
        PageableListView<BeneficiaryGrade> eachItem = new PageableListView<BeneficiaryGrade>("eachItem", model, 20) {
            @Override
            protected void populateItem(ListItem<BeneficiaryGrade> item) {

                PageParameters params = new PageParameters();
                params.add("beneficiaryId", item.getModelObject().getBeneficiary().getId());

                
                item.add(new BookmarkablePageLink("beneficiaryViewPage", BeneficiaryViewPage.class, params).add(new Label("firstName", item.getModelObject().getBeneficiary().getLastName())));
                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(new Label("province"));
                item.add(new Label("sold"));
                
            }
        };

        //add(new PagingNavigator("navigator", eachItem));
        add(eachItem);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
