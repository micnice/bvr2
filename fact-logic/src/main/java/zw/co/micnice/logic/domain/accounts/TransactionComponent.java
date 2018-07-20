package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Judge Muzinda
 * @author Charles Chigoriwa
 */
@Entity
@Table(name="transactioncomponent")
public class TransactionComponent extends BaseIdEntity implements Serializable {
    
    private Account account;
    private TransactionHeader transactionHeader;
    private BigDecimal amount;
    private Date dueDate;
    private Long version;

    @ManyToOne
    @JoinColumn(name="account_id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    @JoinColumn(name="transaction_header_id")
    @ManyToOne
    public TransactionHeader getTransactionHeader() {
        return transactionHeader;
    }

    public void setTransactionHeader(TransactionHeader transactionHeader) {
        this.transactionHeader = transactionHeader;
    }
    
}