package zw.co.micnice.logic.domain.accounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name = "receiptheader")
public class ReceiptHeader extends BaseIdEntity {

    private Set<ReceiptItem> receiptItems = new HashSet<ReceiptItem>();
    private Date date;
    private BigDecimal totalAmount; //total amount paid
    private TransactionType transactionType;
    private Long generatedReceiptNumber;
    private PaymentDetails paymentDetails;
    private Boolean reversed = Boolean.FALSE;

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @OneToMany(mappedBy = "receiptHeader")
    public Set<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(Set<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

    @ManyToOne
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @OneToOne
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    @Column(unique = true)
    public Long getGeneratedReceiptNumber() {
        return generatedReceiptNumber;
    }

    public void setGeneratedReceiptNumber(Long generatedReceiptNumber) {
        this.generatedReceiptNumber = generatedReceiptNumber;
    }

    @Transient //Total items cost
    public BigDecimal getTotalItemsCost() {
        BigDecimal total = new BigDecimal(0);
        for (ReceiptItem item : getReceiptItems()) {
            total = total.add(item.getAmount());
        }
        return total;
    }

    @Transient //Total amount paid - value to be shown of receipt
    public BigDecimal getTotalAmountPaid() {
        BigDecimal total = new BigDecimal(0);
        for (ReceiptItem item : getReceiptItems()) {
            total = total.add(item.getAmount());
        }
        return total;
    }

    @Transient //total amount paid - receipt items total
    public BigDecimal getBalance() {
        if (totalAmount == null) {
            return new BigDecimal(0);
        }
        if (totalAmount.doubleValue() == getTotalAmountPaid().doubleValue()) {
            return new BigDecimal(0);
        } else {
            return totalAmount.subtract(getTotalAmountPaid());
        }
    }

    @Transient //carry forward
    public BigDecimal getCarryForward() {
        if (totalAmount == null) {
            return new BigDecimal(0);
        }
        if (totalAmount.doubleValue() == getTotalAmountPaid().doubleValue()) {
            return new BigDecimal(0);
        } else {
            return totalAmount.subtract(getTotalAmountPaid());
        }
    }
    
    @Transient
    public String getStatus() {
        return reversed ? "Canceled" : "";
    }
}
