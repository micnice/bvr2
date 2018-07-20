package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author Edward Zengeni
 */
@Entity
@Table(name="transactionheader")
public class TransactionHeader implements Serializable {
    private Set<TransactionComponent> transactionComponents=new HashSet<TransactionComponent>();

    private static final long serialVersionUID = 1L;
    private Long id;
    private TransactionType transactionType;
    private Date dueDate;
    private String description;
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

   

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate= dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
      final  TransactionHeader other = (TransactionHeader) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @OneToMany(mappedBy = "transactionHeader"/*,cascade = CascadeType.PERSIST*/)
    public Set<TransactionComponent> getTransactionComponents() {
        return transactionComponents;
    }

    public void setTransactionComponents(Set<TransactionComponent> transactionComponents) {
        this.transactionComponents = transactionComponents;
    }
    

   
}
