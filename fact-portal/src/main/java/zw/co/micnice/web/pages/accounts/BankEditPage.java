package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.Bank;
import zw.co.micnice.logic.service.accounts.BankService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class BankEditPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private BankService bankService;

    public BankEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<Bank>(new LoadableDetachableBankModel(id)));
        Form<Bank> form = new Form<Bank>("form", (IModel<Bank>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                bankService.save(getModelObject());
                setResponsePage(new BankViewPage(getModelObject().getId()));
            }
        };

        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior()));
        form.add(new TextField<String>("accNumber").add(new ErrorBehavior()));
        form.add(new TextField<String>("bank").add(new ErrorBehavior()));
        form.add(new TextField<String>("branch").add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("btnCancel", BankListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }

    private final class LoadableDetachableBankModel extends LoadableDetachableModel<Bank> {

        private Long id;

        public LoadableDetachableBankModel(Long id) {
            this.id = id;
        }

        @Override
        protected Bank load() {
            if (id == null) {
                return new Bank();
            }
            return bankService.get(id);
        }
    }
}
