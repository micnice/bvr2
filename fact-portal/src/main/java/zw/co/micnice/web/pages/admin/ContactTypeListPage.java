package zw.co.micnice.web.pages.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.ContactType;
import zw.co.micnice.logic.domain.Contribution;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.ContactTypeService;
import zw.co.micnice.logic.service.ContributionService;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Matiashe Michael
 */
public class ContactTypeListPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private ContactTypeService contactTypeService;
    @SpringBean
    private ContributionService contributionService;
    @SpringBean
    private BeneficiaryService beneficiaryService;

    public ContactTypeListPage() {
        
        IModel<List<Contribution>> model = new LoadableDetachableModel<List<Contribution>>() {
            @Override
            protected List<Contribution> load() {
                List<Beneficiary> beneficiarys = beneficiaryService.getClosedItems();
                List<Contribution> topBids = new ArrayList<Contribution>();
                for (Beneficiary beneficiary : beneficiarys) {
                   Contribution contribution = contributionService.getTopBid(beneficiary);
                    if(contribution!=null){
                        topBids.add(contribution);
                    }
                }
                return topBids;
            }
        };

        PropertyListView<Contribution> eachItem = new PropertyListView<Contribution>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<Contribution> item) {
               
                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(new Label("beneficiary.firstName"));
                item.add(new Label("number"));
                item.add(new Label("amount"));
            }
        };
        add(eachItem);
    }
}
