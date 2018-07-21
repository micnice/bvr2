package zw.co.micnice.web.pages.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
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
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Matiashe Michael
 */
public class ContactTypeListPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private ContactTypeService contactTypeService;

    public ContactTypeListPage() {

        IModel<List<ContactType>> model = new LoadableDetachableModel<List<ContactType>>() {
            @Override
            protected List<ContactType> load() {
               return contactTypeService.findAll();
            }
        };

        PropertyListView<ContactType> eachItem = new PropertyListView<ContactType>("eachItem", model) {
            @Override
            protected void populateItem(ListItem<ContactType> item) {
               
                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.add(new Label("name"));
                item.add(new Label("description"));
            }
        };
        add(eachItem);
    }
}
