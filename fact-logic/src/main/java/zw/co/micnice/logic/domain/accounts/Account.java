package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Tatenda Chiwandire
 * @author Charles Chigoriwa
 * @author Edward Zengeni
 */
@Entity
@Table(name="account")
public class Account extends BaseIdEntity implements Serializable {

    private AccountType accountType;
    private String code;
    private String name;
    private BigDecimal balance= BigDecimal.ZERO;
    private Boolean personalAccount = Boolean.FALSE;
    private long version;

    public Boolean getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(Boolean personalAccount) {
        this.personalAccount = personalAccount;
    }
    
    @Enumerated(EnumType.STRING)
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    
    
    @Override
    public String toString() {
        return getName();
    }
    
    public void debit(BigDecimal amount){
        if(BalanceType.DR.equals(this.accountType.getBalanceType())){
            balance=balance.add(amount);
        }else{
            balance=balance.subtract(amount);
        }
    }
    
    public void credit(BigDecimal amount){
        if(BalanceType.CR.equals(this.accountType.getBalanceType())){
            balance=balance.add(amount);
        }else{
            balance=balance.subtract(amount);
        }
    }
    
    
    
}
