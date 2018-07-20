package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="duration")
public class Duration extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date startDate;
    private Date endDate;
    private Boolean active;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @Transient
    public String getTextStatus() {
        if (active == null) {
            return "InActive";
        }
        return active ? "Active" : "InActive";
    }
}
