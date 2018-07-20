package zw.co.micnice.web.pages.accounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import zw.co.micnice.web.pages.BeneficiaryViewPage;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.EmptyRequestHandler;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;
import zw.co.micnice.logic.service.accounts.PaymentDetailsService;
import zw.co.micnice.logic.service.accounts.ReceiptHeaderService;
import zw.co.micnice.logic.utils.HrisComparator;
import zw.co.micnice.reports.Receipt;
import zw.co.micnice.reports.Report;
import zw.co.micnice.reports.utils.ContentType;
import zw.co.micnice.web.pages.TemplatePage;

/**
 *
 * @author Michael Matiashe
 */
import zw.co.micnice.web.utility.ReportResourceUtils;
public class PaymentConfirmationPage extends TemplatePage {

    @SpringBean
    PaymentDetailsService paymentDetailsService;
    @SpringBean
    private ReceiptHeaderService receiptHeaderService;
    private HrisComparator hrisComparator = new HrisComparator();
    private Boolean show = Boolean.FALSE;
    

    public PaymentConfirmationPage(final PaymentDetails paymentDetails, final Account personalAccount) {

        final CompoundPropertyModel<PaymentDetails> model = new CompoundPropertyModel<PaymentDetails>(new PaymentConfirmationPage.LoadableDetachablePaymentDetailsModel(paymentDetails.getId()));
        setDefaultModel(model);
        add(new Label("receiptHeader.id"));
        add(new Label("receiptHeader.date"));
        add(new Label("receiptHeader.totalAmountPaid"));
        add(new Label("receiptHeader.carryForward"));
        add(new Label("receiptHeader.transactionType"));
        add(new Label("receiptHeader.generatedReceiptNumber"));
        add(new Link<ReceiptHeader>("print") {
            @Override
            public void onClick() {
                print(paymentDetails, paymentDetails.getBeneficiary());
            }
        });
        add(new Link<Beneficiary>("beneficiaryViewPage") {
            @Override
            public void onClick() {

                if (paymentDetails.getBeneficiary() != null) {
                    setResponsePage(new BeneficiaryViewPage(paymentDetails.getBeneficiary().getId()));
                }

            }
        });
        add(new FeedbackPanel("errorMessage"));
        add(new Label("beneficiary.beneficiaryName"));

    }
    
    private void print(PaymentDetails paymentDetails, Beneficiary beneficiary) {
        try {
            Receipt receipt = new Receipt();
            ContentType contentType = ContentType.PDF;
            Map parameters = new HashMap();
            
                parameters.put("beneficiaryNumber", beneficiary.getFirstName().concat(" "+ beneficiary.getId()));
            ReceiptHeader  receiptHeader = receiptHeaderService.get(paymentDetails.getReceiptHeader().getId());
            if (receiptHeader.getCarryForward().equals(BigDecimal.ZERO)) {
                parameters.put("comment", "");
            } else {
                if (receiptHeader.getCarryForward().compareTo(new BigDecimal("0")) == -1) {
                    parameters.put("comment", "");
                } else {

                    parameters.put("comment", "CARRY FORWARD ($) " + receiptHeader.getCarryForward().toString());

                }
            }

            parameters.put("beneficiaryName", beneficiary.getFirstName());

            ByteArrayResource resource = ReportResourceUtils.getReportResource(receipt, contentType, hrisComparator.receiptItems(new ArrayList<ReceiptItem>(receiptHeader.getReceiptItems())), parameters);
            IResource.Attributes a = new IResource.Attributes(RequestCycle.get().getRequest(), RequestCycle.get().getResponse(), null);
            resource.respond(a);
            RequestCycle.get().replaceAllRequestHandlers(new EmptyRequestHandler());
        } catch (JRException ex) {
            ex.printStackTrace(System.out);
        }

    }

    protected void processReport(Report report, ContentType contentType, Collection<?> collection, Map<String, Object> parameters) throws JRException {
        ByteArrayResource resource = ReportResourceUtils.getReportResource(report, contentType, collection, parameters);
        IResource.Attributes a = new IResource.Attributes(RequestCycle.get().getRequest(), RequestCycle.get()
                .getResponse(), null);
        resource.respond(a);

        RequestCycle.get().replaceAllRequestHandlers(new EmptyRequestHandler());
    }
    private final class LoadableDetachablePaymentDetailsModel extends LoadableDetachableModel<PaymentDetails> {

        private Long id;

        public LoadableDetachablePaymentDetailsModel(Long id) {
            this.id = id;
        }

        @Override
        protected PaymentDetails load() {
            return paymentDetailsService.get(id);
        }
    }
}
