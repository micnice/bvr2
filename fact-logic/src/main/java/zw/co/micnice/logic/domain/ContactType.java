package zw.co.micnice.logic.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author charlesc
 */
@Entity
@Table(name="contacttype")
public class ContactType extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;   
    private Beneficiary beneficiary;
    private Contribution contribution;

    @ManyToOne
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    @ManyToOne
    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }
    
    
}
