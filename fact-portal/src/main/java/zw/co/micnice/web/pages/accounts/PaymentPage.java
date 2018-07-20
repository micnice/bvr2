package zw.co.micnice.web.pages.accounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Book;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.*;
import zw.co.micnice.logic.process.PaymentProcess;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.accounts.PaymentDetailsService;
import zw.co.micnice.logic.service.accounts.PaymentMethodService;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;
import zw.co.micnice.logic.utils.CouncilException;
import zw.co.micnice.web.config.CouncilSession;
import zw.co.micnice.web.models.TransactionTypeListModel;
import zw.co.micnice.web.pages.TemplatePage;
import zw.co.micnice.web.utility.CDateTextField;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class PaymentPage extends TemplatePage {

    @SpringBean
    private PaymentMethodService paymentMethodService;
    @SpringBean
    private TransactionTypeService transactionTypeService;
    @SpringBean
    private PaymentProcess paymentProcess;
    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    private PaymentDetailsService paymentDetailsService;
    private PaymentMethod paymentMethod;
    private List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
    private CDateTextField dateOfDeposit;
    private TextField<Long> generatedReceiptNumber;

    public PaymentPage(final Beneficiary beneficiary, final List<DebtComponent> debtComponents) {
        initReceiptItems(debtComponents);

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setTransactionHeader(new TransactionHeader());
        paymentDetails.setReceiptHeader(new ReceiptHeader());
        paymentDetails.getReceiptHeader().setCreatedBy(CouncilSession.get().getUser());
        paymentDetails.setBeneficiary(beneficiary);
        paymentDetails.setCreatedBy(CouncilSession.get().getUser());
        paymentDetails.setDateCreated(new Date());
        paymentDetails.setDateOfPayment(new Date());
        setDefaultModel(new CompoundPropertyModel<PaymentDetails>(paymentDetails));
        Form<PaymentDetails> form = new Form<PaymentDetails>("form", (IModel<PaymentDetails>) getDefaultModel()) {
            @Override
            public void onSubmit() {
                try {
                    getModelObject().setPaymentMethod(paymentMethod);

                    PaymentDetails $paymentDetails = this.getModelObject();

                    PaymentMethod paymentMethod = $paymentDetails.getPaymentMethod();
                    if (paymentMethod.getDepositDateRequired() && $paymentDetails.getDateOfDeposit() == null) {
                        dateOfDeposit.error("Date of Deposit required");
                        return;
                    }


                    if (paymentMethod.getDepositDateRequired() && $paymentDetails.getDateOfDeposit() == null) {
                        if ($paymentDetails.getDateOfDeposit().after(new Date())) {
                            error("Deposit date must not be a future date");
                            return;
                        }
                    }

                    if (paymentMethod.getReceiptNumberRequired() && $paymentDetails.getReceiptHeader().getGeneratedReceiptNumber() == null) {
                        generatedReceiptNumber.error("Manually Entered Receipt Reference Number is required");
                        return;
                    }
                    PaymentDetails paymentDetails = paymentProcess.processPayment(getModelObject(), receiptItems);
                    setResponsePage(new PaymentConfirmationPage(paymentDetails, new Account()));
                } catch (CouncilException ex) {
                    error(ex.getMessage());
                }
            }
        };
        form.add(new CDateTextField("dateOfPayment").setRequired(true).add(new ErrorBehavior()).add(new DatePicker()));
        dateOfDeposit = new CDateTextField("dateOfDeposit");
        dateOfDeposit.add(new ErrorBehavior()).add(new DatePicker());
        form.add(dateOfDeposit);
        form.add(new TextField<String>("receiptHeader.totalAmount").setRequired(true).add(new ErrorBehavior()));
        generatedReceiptNumber = new TextField<Long>("receiptHeader.generatedReceiptNumber");
        generatedReceiptNumber.add(new ErrorBehavior());
        form.add(generatedReceiptNumber);
        DropDownChoice<PaymentMethod> paymentMethods = new DropDownChoice<PaymentMethod>("paymentMethod",
                new PropertyModel<PaymentMethod>(this, "paymentMethod"), new PaymentPage.PaymentMethodModel(),
                new ChoiceRenderer<PaymentMethod>("name", "id")) {
            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }

            @Override
            protected void onSelectionChanged(PaymentMethod newSelection) {
                PaymentDetails paymentDetails = (PaymentDetails) PaymentPage.this.getDefaultModelObject();

                paymentDetails.getTransactionHeader().setTransactionType(null);
            }
        };

        paymentMethods.setRequired(true).add(new ErrorBehavior());
        form.add(paymentMethods);

        DropDownChoice<TransactionType> transactionTypes = new DropDownChoice<TransactionType>("transactionHeader.transactionType", new PaymentPage.TransactionTypeModel(),
                new ChoiceRenderer<TransactionType>("name", "id"));
        form.add(transactionTypes);

        add(form);
        form.add(new ReceiptItemListView("receiptItems", new LoadableDetachableModel<List<? extends ReceiptItem>>() {
            @Override
            protected List<? extends ReceiptItem> load() {
                return receiptItems;
            }
            //this.receiptItems
        }));
        add(new FeedbackPanel("errorMessage"));
        add(new Label("beneficiary.beneficiaryName"));
        add(new Label("beneficiary.account.balance"));
        form.add(new Link<Void>("btnCancel") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiary.getId()));
            }
        });
    }

    private final class ReceiptItemListView extends ListView<ReceiptItem> {

        public ReceiptItemListView(String id) {
            super(id);
            setReuseItems(true);
        }

        public ReceiptItemListView(String id, IModel<? extends List<? extends ReceiptItem>> model) {
            super(id, model);
            setReuseItems(true);
        }

        public ReceiptItemListView(String id, List<? extends ReceiptItem> list) {
            super(id, list);
            setReuseItems(true);
        }

        @Override
        protected void populateItem(final ListItem<ReceiptItem> item) {
            item.setDefaultModel(new CompoundPropertyModel<ReceiptItem>(item.getModelObject()));
            item.add(new TextField<BigDecimal>("amount").setRequired(true).add(new ErrorBehavior()));
            item.add(new Label("debtComponent.remainingBalance"));
            item.add(new Label("debtComponent.transactionComponent.account.name"));
        }
    }

    private void initReceiptItems(List<DebtComponent> debtComponents) {
        for (DebtComponent debtComponent : debtComponents) {
            ReceiptItem receiptItem = new ReceiptItem();
            receiptItem.setDebtComponent(debtComponent);
            receiptItem.setAmount(debtComponent.getRemainingBalance());
            receiptItem.setCreatedBy(CouncilSession.get().getUser());
            receiptItem.setDateCreated(new Date());
            receiptItems.add(receiptItem);
        }
    }

    private class TransactionTypeModel extends LoadableDetachableModel<List<? extends TransactionType>> {

        protected List<? extends TransactionType> load() {
            if (paymentMethod != null) {
                return new TransactionTypeListModel(transactionTypeService, paymentMethod, Book.CB).getObject();
            } else {
                return new ArrayList<TransactionType>();
            }
        }
    }

    private final class PaymentMethodModel extends LoadableDetachableModel<List<? extends PaymentMethod>> {

        protected List<? extends PaymentMethod> load() {
            return paymentMethodService.findAll();
        }
    }
}
