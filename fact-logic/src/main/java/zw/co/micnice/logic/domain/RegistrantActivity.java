package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import zw.co.micnice.logic.domain.accounts.DebtComponent;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@Table(name="registrantactivity")
public class RegistrantActivity extends BaseEntity implements Serializable {
    
    private Registrant registrant;
    private RegistrantActivityType registrantActivityType;
    private Date startDate;
    private Date endDate; //Expiry date or expected date of expiry    
    private DebtComponent transactionDebtComponent;
    private DebtComponent penaltyDebtComponent;    
    private boolean ended;
    private Duration duration;
    private String firstName;
    private String middleName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }     
    
   @OneToOne
    public Registrant getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Registrant registrant) {
        this.registrant = registrant;
    }
    @Enumerated(EnumType.STRING)
    public RegistrantActivityType getRegistrantActivityType() {
        return registrantActivityType;
    }

    public void setRegistrantActivityType(RegistrantActivityType registrantActivityType) {
        this.registrantActivityType = registrantActivityType;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToOne
    public DebtComponent getTransactionDebtComponent() {
        return transactionDebtComponent;
    }

    public void setTransactionDebtComponent(DebtComponent transactionDebtComponent) {
        this.transactionDebtComponent = transactionDebtComponent;
    }

    @OneToOne
    public DebtComponent getPenaltyDebtComponent() {
        return penaltyDebtComponent;
    }

    public void setPenaltyDebtComponent(DebtComponent penaltyDebtComponent) {
        this.penaltyDebtComponent = penaltyDebtComponent;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
    
    @ManyToOne
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    
    
}
