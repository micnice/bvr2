package zw.co.micnice.web.pages.admin;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

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
