package zw.co.micnice.logic.process;

import java.io.Serializable;
import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.Document;

/**
 *
 * @author Michael Matiashe
 */
public interface CreditNoteProcess extends Serializable {
    public void processCreditNote(Beneficiary beneficiary, List<DebtComponent> debtComponents);
}
