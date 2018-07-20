package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseEntity;
import zw.co.micnice.logic.domain.Book;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="transactiontype")
public class TransactionType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Account drLedger;
    private Account crLedger;
    private Effect effect;
    private Book book;
    private PaymentMethod paymentMethod;

    @ManyToOne
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Enumerated(EnumType.STRING)
    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
    
   
    @ManyToOne
    public Account getDrLedger() {
        return drLedger;
    }

    public void setDrLedger(Account drLedger) {
        this.drLedger = drLedger;
    }

    @ManyToOne
    public Account getCrLedger() {
        return crLedger;
    }

    public void setCrLedger(Account crLedger) {
        this.crLedger = crLedger;
    }

    @Enumerated(EnumType.STRING)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    
}
