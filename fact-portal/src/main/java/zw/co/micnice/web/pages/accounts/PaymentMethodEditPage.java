package zw.co.micnice.web.pages.accounts;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class PaymentMethodEditPage extends IAdministerDatabaseBasePage{
    
    @SpringBean
    private PaymentMethodService paymentMethodService;

    public PaymentMethodEditPage(Long id) {
        setDefaultModel(new CompoundPropertyModel<PaymentMethod>(new LoadableDetachablePaymentMethodModel(id)));
        Form<PaymentMethod> form=new Form<PaymentMethod>("form",(IModel<PaymentMethod>)getDefaultModel()) {
            @Override
            public void onSubmit(){
                paymentMethodService.save(getModelObject());
                setResponsePage(new PaymentMethodViewPage(getModelObject().getId()));
            }
        };
        
        form.add(new TextField<String>("name").setRequired(true).add(new ErrorBehavior())); 
        form.add(new TextField<String>("description").add(new ErrorBehavior()));
        form.add(new CheckBox("receiptNumberRequired"));
        form.add(new CheckBox("depositDateRequired"));
        form.add(new BookmarkablePageLink("btnCancel", PaymentMethodListPage.class));
        add(form);
        add(new FeedbackPanel("errorMessage"));
    }
    
    
    private final class LoadableDetachablePaymentMethodModel extends LoadableDetachableModel<PaymentMethod> {

        private Long id;

        public LoadableDetachablePaymentMethodModel(Long id) {
            this.id = id;
        }

        @Override
        protected PaymentMethod load() {
            if(id==null){
                return new PaymentMethod();
            }
            return paymentMethodService.get(id);
        }
    }
    
    
}
