package zw.co.micnice.web.pages;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.ContactTypeService;
import zw.co.micnice.web.config.CouncilSession;
import zw.co.micnice.web.pages.admin.AdministerDatabasePage;
import zw.co.micnice.web.pages.admin.ContactTypeListPage;
import zw.co.micnice.web.search.SearchBeneficiaryPage;

/**
 * Template Page for all the system pages.
 *
 */
public abstract class TemplatePage extends WebPage {

    private String userName = CouncilSession.get().getUser().getUsername().trim();
    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    private ContactTypeService contactTypeService;

    public TemplatePage() {
        this.add(new JQueryBehavior("#tabs", "tabs"));
        add(new Label("loggedInUserNameLabel", userName));
        add(new BookmarkablePageLink("addBeneficiaryLink", BeneficiaryEditPage.class));
        add(new BookmarkablePageLink("admin", AdministerDatabasePage.class));
        add(new BookmarkablePageLink("logout", SignOutPage.class));
        add(new BookmarkablePageLink("searchLink", SearchBeneficiaryPage.class));
        add(new BookmarkablePageLink("companiesListLink", BeneficiaryListPage.class));
        add(new BookmarkablePageLink("topBids", ContactTypeListPage.class));
        add(new Link("auct") {

            @Override
            public void onClick() {

            }
        });

        add(new Link("send") {

            @Override
            public void onClick() {

            }
        });

    }



}
