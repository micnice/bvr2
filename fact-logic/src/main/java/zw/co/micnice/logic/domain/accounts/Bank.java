package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="bank")
public class Bank extends BaseIdEntity implements Serializable{
    
    private String name;   
    private String accNumber;
    private String bank;
    private String branch;
    
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
    
    
    
}
