package zw.co.micnice.web.pages;

import zw.co.micnice.web.pages.admin.IAdministerDatabaseBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.process.InvoiceProcess;
import zw.co.micnice.logic.service.BeneficiaryGradeService;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.web.search.SearchBeneficiaryPage;

/**
 *
 * @author Matiashe Michael
 */
public class BeneficiaryViewPage extends IAdministerDatabaseBasePage {

    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    private BeneficiaryGradeService beneficiaryGradeService;
    @SpringBean
    private InvoiceProcess invoiceProcess;

    public BeneficiaryViewPage(PageParameters parameters) {
        this(parameters.get("beneficiaryId").toLong());
    }

    public BeneficiaryViewPage(Long id) {
        final CompoundPropertyModel<Beneficiary> model = new CompoundPropertyModel<Beneficiary>(new LoadableDetachableBeneficiaryModel(id));
        setDefaultModel(model);
        add(new Link<Beneficiary>("editLink", model) {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryEditPage(model.getObject().getId()));
            }
        });
        add(new Label("firstName"));
        add(new Label("lastName"));
        add(new BookmarkablePageLink("btnCancel", SearchBeneficiaryPage.class));
        
    }

    private final class LoadableDetachableBeneficiaryModel extends LoadableDetachableModel<Beneficiary> {

        private Long id;

        public LoadableDetachableBeneficiaryModel(Long id) {
            this.id = id;
        }

        @Override
        protected Beneficiary load() {

            return beneficiaryService.get(id);
        }
    }
}
