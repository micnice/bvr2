
package zw.co.micnice.logic.domain.accounts;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Constance Mabaso
 */
@Entity
@Table(name="allocation")
public class Allocation extends BaseIdEntity {
    
    private TransactionComponent paymentTransactionComponent;
    private TransactionComponent debtTransactionComponent;
    private BigDecimal amount;
    
    private long version;
    
    
    @ManyToOne
    public TransactionComponent getPaymentTransactionComponent() {
        return paymentTransactionComponent;
    }

    public void setPaymentTransactionComponent(TransactionComponent paymentTransactionComponent) {
        this.paymentTransactionComponent = paymentTransactionComponent;
    }

    @ManyToOne
    public TransactionComponent getDebtTransactionComponent() {
        return debtTransactionComponent;
    }

    public void setDebtTransactionComponent(TransactionComponent debtTransactionComponent) {
        this.debtTransactionComponent = debtTransactionComponent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    
    
    
    
}
