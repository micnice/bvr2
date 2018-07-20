package zw.co.micnice.logic.domain.accounts;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import zw.co.micnice.logic.domain.BaseEntity;

/**
 *
 * @author Michael Matiashe
 * @author Charles Chigoriwa
 */
@Entity
@Table(name="receiptitem")
public class ReceiptItem extends BaseEntity {

    private BigDecimal amount;
    private ReceiptHeader receiptHeader;
    private DebtComponent debtComponent;
    private Long code;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne
    public ReceiptHeader getReceiptHeader() {
        return receiptHeader;
    }

    public void setReceiptHeader(ReceiptHeader receiptHeader) {
        this.receiptHeader = receiptHeader;
    }

    @ManyToOne
    public DebtComponent getDebtComponent() {
        return debtComponent;
    }

    public void setDebtComponent(DebtComponent debtComponent) {
        this.debtComponent = debtComponent;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Transient
    public String getCodeValue() {
        if (code == null) {
            return "";
        }
        return String.valueOf(code);
    }
}
