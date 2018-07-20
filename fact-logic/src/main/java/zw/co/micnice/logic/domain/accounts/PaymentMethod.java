package zw.co.micnice.logic.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import zw.co.micnice.logic.domain.BaseEntity;

/**
 *
 * @author Matiashe Michael
 */
@Entity
@Table(name = "paymentmethod")
public class PaymentMethod extends BaseEntity {

    private Boolean receiptNumberRequired = Boolean.FALSE;
    private Boolean depositDateRequired = Boolean.FALSE;

    public Boolean getReceiptNumberRequired() {
        return receiptNumberRequired;
    }

    public void setReceiptNumberRequired(Boolean receiptNumberRequired) {
        this.receiptNumberRequired = receiptNumberRequired;
    }

    @Transient
    public String getReceitNumberRequiredStatus() {
        if (receiptNumberRequired == null) {
            return "";
        } else {
            return receiptNumberRequired ? "YES" : "NO";
        }
    }

    @Transient
    public String getdepositeDateRequiredStatus() {
        if (depositDateRequired == null) {
            return "";
        } else {
            return depositDateRequired ? "YES" : "NO";
        }
    }

    public Boolean getDepositDateRequired() {
        return depositDateRequired;
    }

    public void setDepositDateRequired(Boolean depositDateRequired) {
        this.depositDateRequired = depositDateRequired;
    }
}
