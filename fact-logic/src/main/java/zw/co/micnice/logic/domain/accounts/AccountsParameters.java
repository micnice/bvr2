package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="accountsparameters")
public class AccountsParameters extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private TransactionType invoiceTransactionType;
    private PaymentMethod defaultPaymentMethod;
    private TransactionType creditNoteTransactionType;

    @OneToOne
    public TransactionType getCreditNoteTransactionType() {
        return creditNoteTransactionType;
    }

    public void setCreditNoteTransactionType(TransactionType creditNoteTransactionType) {
        this.creditNoteTransactionType = creditNoteTransactionType;
    }
    
    

    @OneToOne
    public TransactionType getInvoiceTransactionType() {
        return invoiceTransactionType;
    }

    public void setInvoiceTransactionType(TransactionType invoiceTransactionType) {
        this.invoiceTransactionType = invoiceTransactionType;
    }

    @OneToOne
    public PaymentMethod getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }

    public void setDefaultPaymentMethod(PaymentMethod defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }
      
}
