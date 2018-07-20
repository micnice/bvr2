package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import zw.co.micnice.web.pages.accounts.AccountListPage;
import zw.co.micnice.web.pages.accounts.BankListPage;
import zw.co.micnice.web.pages.accounts.PaymentMethodListPage;
import zw.co.micnice.web.pages.accounts.TransactionTypeListPage;

/**
 *
 * @author Michael Matiashe
 */
public class AdministerDatabasePage extends IAdministerDatabaseBasePage {
    
  
    public AdministerDatabasePage() {
        /*
         * Manage
         */
       
        add(new BookmarkablePageLink<Void>("provinceLink", ProvinceListPage.class));
        add(new BookmarkablePageLink<Void>("districtLink", DistrictListPage.class));
       

        /**
         * Accounting
         */
    }
}
