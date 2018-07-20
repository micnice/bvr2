package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;

/**
 *
 * @author Matiashe Michael
 */
public class TransactionTypeViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private TransactionTypeService transactionTypeService;

    public TransactionTypeViewPage(Long id) {
        CompoundPropertyModel<TransactionType> model=new CompoundPropertyModel<TransactionType>(new LoadableDetachableTransactionTypeModel(id));
        setDefaultModel(model);
        add(new Link<TransactionType>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new TransactionTypeEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("name"));
        add(new Label("effect"));
        add(new Label("book"));
        add(new Label("crLedger"));
        add(new Label("drLedger"));
        add(new Label("paymentMethod"));
        add(new BookmarkablePageLink("btnCancel", TransactionTypeListPage.class));
    }


    private final class LoadableDetachableTransactionTypeModel extends LoadableDetachableModel<TransactionType> {

        private Long id;

        public LoadableDetachableTransactionTypeModel(Long id) {
            this.id = id;
        }

        @Override
        protected TransactionType load() {

            return transactionTypeService.get(id);
        }
    }
}
