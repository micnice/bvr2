/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;

/**
 *
 * @author Michael Matiashe
 */
public class PaymentMethodListModel extends LoadableDetachableModel<List<PaymentMethod>> {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodListModel(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    protected List<PaymentMethod> load() {
       return paymentMethodService.findAll();
    }
    
}
