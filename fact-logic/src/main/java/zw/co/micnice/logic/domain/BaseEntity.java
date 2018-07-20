package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.utils.CaseUtil;

/**
 *
 * @author Michael Matiashe
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private Long id;
    private String name;
    private String description;
    private User createdBy;
    private User modifiedBy;
    private Date dateCreated;
    private Date dateModified;
    private String uid = UUID.randomUUID().toString();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return CaseUtil.upperCase(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return CaseUtil.upperCase(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne
    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Transient
    public String getStringIdValue() {
        return String.valueOf(id);
    }
}
