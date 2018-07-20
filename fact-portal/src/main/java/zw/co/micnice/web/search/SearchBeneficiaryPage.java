package zw.co.micnice.web.search;

//~--- non-JDK imports --------------------------------------------------------
import zw.co.micnice.web.pages.panels.BeneficiaryDataViewPanelList;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.web.pages.BeneficiaryEditPage;
import zw.co.micnice.web.pages.TemplatePage;
import zw.co.micnice.web.utility.ErrorBehavior;

/**
 *
 * @author Matiashe Michael
 */
public class SearchBeneficiaryPage extends TemplatePage {

    private String searchtxt;
    @SpringBean
    private BeneficiaryService beneficiaryService;

    public SearchBeneficiaryPage() {

        PropertyModel<String> messageModel = new PropertyModel<String>(this, "searchtxt");
        Form<?> form = new Form("form");

        form.add(new TextField<String>("searchtxt", messageModel).setRequired(true).add(new ErrorBehavior()));
        form.add(new BookmarkablePageLink("beneficiaryAddPage",BeneficiaryEditPage.class));
        add(form);
        
        

        IModel<List<Beneficiary>> model = new LoadableDetachableModel<List<Beneficiary>>() {
            @Override
            protected List<Beneficiary> load() {
                if ((searchtxt == null) || searchtxt.trim().equals("")) {
                    return new ArrayList<Beneficiary>();
                }

                if (searchtxt.length() > 2) {
                    return beneficiaryService.getCompanies(searchtxt.trim());
                }

                return new ArrayList<Beneficiary>();
            }
        };

        // Beneficiary List Data View Panel
        add(new BeneficiaryDataViewPanelList("beneficiaryDataListPanel", model));
        add(new FeedbackPanel("errorMessage"));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
