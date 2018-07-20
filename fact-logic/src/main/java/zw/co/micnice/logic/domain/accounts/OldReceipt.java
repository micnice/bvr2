/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain.accounts;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import zw.co.micnice.logic.domain.BaseIdEntity;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.User;

/**
 *
 * @author tdhlakama
 */
@Entity
@Table(name="oldreceipt")
public class OldReceipt extends BaseIdEntity {

    private Product product;
    private Double lineTotal;
    private Long receiptRecordID;
    private User user;
    private Date dateOfReceipt;
    private PaymentMethod paymentMethod;
    private PaymentType paymentType;
    private String paymentPurpose;
    private Boolean cancelled;
    private Boolean bankManulPayment;
    private Date transactionDate;
    private Registrant registrant;
    private String sourceReference;

    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Long getReceiptRecordID() {
        return receiptRecordID;
    }

    public void setReceiptRecordID(Long receiptRecordID) {
        this.receiptRecordID = receiptRecordID;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    @ManyToOne
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @ManyToOne
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getBankManulPayment() {
        return bankManulPayment;
    }

    public void setBankManulPayment(Boolean bankManulPayment) {
        this.bankManulPayment = bankManulPayment;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @ManyToOne
    public Registrant getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Registrant registrant) {
        this.registrant = registrant;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getSourceReference() {
        return sourceReference;
    }

    public void setSourceReference(String sourceReference) {
        this.sourceReference = sourceReference;
    }
}
