
package zw.co.micnice.logic.service.accounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import zw.co.micnice.logic.dao.accounts.impl.DocumentDAOImpl;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Michael Matiashe

 */
public interface DebtComponentService extends IGenericService<DebtComponent> {
    public List<DebtComponent> getDeptComponents(Document document);
    List<DebtComponent> getDebtComponents(Account account);
    List<DebtComponent> getDebtComponents(Customer customerAccount);
    public List<DebtComponent> getDebtComponents(Beneficiary beneficiary);
    public List<DebtComponent> getCurrentDebtComponents(Beneficiary beneficiary);
    public List<DebtComponent> getOneMonthDebtComponents(Account account);
    public List<DebtComponent> getTwoMonthDebtComponents(Account account);
    public List<DebtComponent> getThreeMonthsOrMoreDebtComponents(Account account);
    public int getTotalDues(Account account, Boolean threeOrMore, Boolean twoMonths, Boolean oneMonth, Boolean current);
    List<DebtComponent> getDebtComponents(Product product, Date startDate, Date endDate);
}