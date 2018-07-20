
package zw.co.micnice.web.pages.accounts;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentType;
import zw.co.micnice.logic.process.CreditNoteProcess;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.logic.service.accounts.DocumentService;
import zw.co.micnice.web.pages.BeneficiaryViewPage;
import zw.co.micnice.web.pages.TemplatePage;
import zw.co.micnice.web.util.DetachableBeneficiaryModel;
import zw.co.micnice.web.utility.EvenTableRowBehavior;

/**
 *
 * @author Michael Matiashe
 */
public class InvoiceListPage extends TemplatePage {

    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    AccountService accountService;
    @SpringBean
    private BeneficiaryService beneficiaryAccountService;
    @SpringBean
    private DocumentService documentService;
    @SpringBean
    private CreditNoteProcess creditNoteProcess;
    @SpringBean
    private DebtComponentService debtComponentService;
    
    public InvoiceListPage(PageParameters parameters) {
        this(parameters.get("beneficiaryId").toLong());
    }

    public InvoiceListPage(Long id) {

        final CompoundPropertyModel<Beneficiary> beneficiaryModel = new CompoundPropertyModel<Beneficiary>(new DetachableBeneficiaryModel(id, beneficiaryAccountService));
        setDefaultModel(beneficiaryModel);
        add(new Label("beneficiaryName"));

        IModel<List<Document>> documentModel = new LoadableDetachableModel<List<Document>>() {
            @Override
            protected List<Document> load() {
                
                return documentService.getDocuments(beneficiaryModel.getObject(), DocumentType.INVOICE);
            }
        };

        
        PropertyListView<Document> eachItem = new PropertyListView<Document>("eachItem", documentModel) {
            @Override
            protected void populateItem(final ListItem<Document> item) {

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }
                
                Link<Void> reverseLink = new Link<Void>("reverse") {
                    @Override
                    public void onClick() {
                      creditNoteProcess.processCreditNote(beneficiaryModel.getObject(), debtComponentService.getDeptComponents(item.getModelObject()) );
                      Document document = item.getModelObject();
                      document.setReversed(Boolean.TRUE);
                      documentService.save(document);
                      setResponsePage(new BeneficiaryViewPage(beneficiaryModel.getObject().getId()));
                    }
                };
                item.add(new Label("documentType"));
                item.add(new Label("id"));
                item.add(new Label("date"));
                item.add(reverseLink);
            }
        };
        add(eachItem);
        add(new Link("beneficiaryViewPage") {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryViewPage(beneficiaryModel.getObject().getId()));
            }
        });
    }

    
}
