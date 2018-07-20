package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@Table(name="customer")
public class Customer extends BaseIdEntity implements Serializable {

    private String code;
    private String customerName;
    private Account account;

    @Column(unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @OneToOne
    @Cascade(CascadeType.PERSIST)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return code;
    }
}
