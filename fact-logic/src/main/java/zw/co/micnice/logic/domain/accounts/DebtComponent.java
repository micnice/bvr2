package zw.co.micnice.logic.domain.accounts;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseIdEntity;
import zw.co.micnice.logic.domain.BeneficiaryGrade;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="debtcomponent")
public class DebtComponent extends BaseIdEntity {
    
    private TransactionComponent transactionComponent;
    private BigDecimal remainingBalance;
    private Account account;
    private Product product;
    private BeneficiaryGrade beneficiaryGrade;
    private Document document;
    private Boolean reversed = Boolean.FALSE;

    @OneToOne
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @OneToOne
    public BeneficiaryGrade getBeneficiaryGrade() {
        return beneficiaryGrade;
    }

    public void setBeneficiaryGrade(BeneficiaryGrade beneficiaryGrade) {
        this.beneficiaryGrade = beneficiaryGrade;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    @OneToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    

    @OneToOne
    public TransactionComponent getTransactionComponent() {
        return transactionComponent;
    }

    public void setTransactionComponent(TransactionComponent transactionComponent) {
        this.transactionComponent = transactionComponent;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
    
    
    public void reduceRemainingBalance(BigDecimal amount){
       this.remainingBalance=this.remainingBalance.subtract(amount);
    }
    
    
    
}
