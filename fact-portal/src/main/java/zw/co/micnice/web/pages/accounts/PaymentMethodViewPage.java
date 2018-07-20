package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;

/**
 *
 * @author Matiashe Michael
 */
public class PaymentMethodViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private PaymentMethodService paymentMethodService;

    public PaymentMethodViewPage(Long id) {
        CompoundPropertyModel<PaymentMethod> model=new CompoundPropertyModel<PaymentMethod>(new LoadableDetachablePaymentMethodModel(id));
        setDefaultModel(model);
        add(new Link<PaymentMethod>("editLink",model){

            @Override
            public void onClick() {
                setResponsePage(new PaymentMethodEditPage(getModelObject().getId()));
            }
            
        });
        add(new Label("name"));
        add(new Label("description"));
        add(new BookmarkablePageLink("btnCancel", PaymentMethodListPage.class));
    }


    private final class LoadableDetachablePaymentMethodModel extends LoadableDetachableModel<PaymentMethod> {

        private Long id;

        public LoadableDetachablePaymentMethodModel(Long id) {
            this.id = id;
        }

        @Override
        protected PaymentMethod load() {

            return paymentMethodService.get(id);
        }
    }
}
