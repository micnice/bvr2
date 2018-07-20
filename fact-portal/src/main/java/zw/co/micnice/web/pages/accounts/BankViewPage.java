package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.Bank;
import zw.co.micnice.logic.service.accounts.BankService;

/**
 *
 * @author charlesc
 */
public class BankViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private BankService bankService;

    public BankViewPage(Long id) {
        CompoundPropertyModel<Bank> model=new CompoundPropertyModel<Bank>(new LoadableDetachableBankModel(id));
        setDefaultModel(model);
        add(new Link<Bank>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new BankEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("name"));
        add(new Label("accNumber"));
        add(new Label("branch"));
        add(new Label("bank"));
        add(new BookmarkablePageLink("btnCancel", BankListPage.class));
    }


    private final class LoadableDetachableBankModel extends LoadableDetachableModel<Bank> {

        private Long id;

        public LoadableDetachableBankModel(Long id) {
            this.id = id;
        }

        @Override
        protected Bank load() {

            return bankService.get(id);
        }
    }
}
