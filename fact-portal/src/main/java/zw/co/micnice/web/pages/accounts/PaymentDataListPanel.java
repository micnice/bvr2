/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.pages.accounts;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;
import zw.co.micnice.logic.process.ReversePaymentProcess;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.accounts.PaymentDetailsService;
import zw.co.micnice.logic.service.accounts.ReceiptHeaderService;
import zw.co.micnice.logic.utils.HrisComparator;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author tdhlakama
 */
public class PaymentDataListPanel extends Panel {

    @SpringBean
    ReceiptHeaderService receiptHeaderService;
    @SpringBean
    PaymentDetailsService paymentDetailsService;
    private HrisComparator hrisComparator = new HrisComparator();
    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    ReversePaymentProcess reversePaymentProcess;

    public PaymentDataListPanel(String id, IModel<List<ReceiptHeader>> model) {
        super(id);
        PageableListView<ReceiptHeader> eachItem = new PageableListView<ReceiptHeader>("eachItem", model, 20) {
            @Override
            protected void populateItem(ListItem<ReceiptHeader> item) {
                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                item.setModel(new CompoundPropertyModel<ReceiptHeader>(item.getModel()));
                item.add(new Label("id"));
                item.add(new Label("date"));
                item.add(new Label("totalAmountPaid"));
                item.add(new Label("carryForward"));
                item.add(new Label("transactionType"));
                item.add(new Label("generatedReceiptNumber"));
                final ReceiptHeader header = item.getModelObject();
                
                
            }
        };
        add(new PagingNavigator("navigator", eachItem));
        add(eachItem);
    }

}
