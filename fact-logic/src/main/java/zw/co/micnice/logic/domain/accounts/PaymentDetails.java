package zw.co.micnice.logic.domain.accounts;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import zw.co.micnice.logic.domain.BaseIdEntity;
import zw.co.micnice.logic.domain.Beneficiary;

/**
 *
 * @author Matiashe Michael
 */
@Entity
@Table(name="paymentdetails")
public class PaymentDetails extends BaseIdEntity{

    private Beneficiary beneficiary;
    private PaymentMethod paymentMethod;
    private Date dateOfPayment;
    private Date dateOfDeposit;
    private Account bankAccount;
    private TransactionHeader transactionHeader;
    private ReceiptHeader receiptHeader;
    private PaymentType paymentType;

    @ManyToOne
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary= beneficiary;
    }

    @OneToOne
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @OneToOne(mappedBy = "paymentDetails")
    public ReceiptHeader getReceiptHeader() {
        return receiptHeader;
    }

    public void setReceiptHeader(ReceiptHeader receiptHeader) {
        this.receiptHeader = receiptHeader;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateOfDeposit() {
        return dateOfDeposit;
    }

    public void setDateOfDeposit(Date dateOfDeposit) {
        this.dateOfDeposit = dateOfDeposit;
    }

    @OneToOne
    public TransactionHeader getTransactionHeader() {
        return transactionHeader;
    }

    public void setTransactionHeader(TransactionHeader transactionHeader) {
        this.transactionHeader = transactionHeader;
    }

    @ManyToOne
    public Account getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

   

    @ManyToOne
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    
   
}
