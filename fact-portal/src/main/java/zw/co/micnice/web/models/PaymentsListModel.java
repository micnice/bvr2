package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;
import zw.co.micnice.logic.service.accounts.PaymentDetailsService;

/**
 *
 * @author Michael Matiashe
 */
public class PaymentsListModel extends LoadableDetachableModel<List<PaymentDetails>> {
    
    private final PaymentDetailsService paymentDetailsService;

    public PaymentsListModel(PaymentDetailsService paymentDetailsService) {
        this.paymentDetailsService = paymentDetailsService;
    }
    
    @Override
    protected List<PaymentDetails> load() {
      return this.paymentDetailsService.findAll();
    }
    
}
