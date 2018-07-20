package zw.co.micnice.logic.process;

import java.io.Serializable;
import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.accounts.DebtComponent;

/**
 *
 * @author Michael Matiashe
 */
public interface InvoiceProcess extends Serializable {
    public void invoice(Beneficiary beneficiary, List<BeneficiaryGrade> beneficiaryGrades);
    public DebtComponent invoice(Beneficiary beneficiary, BeneficiaryGrade beneficiaryGrade);
}
