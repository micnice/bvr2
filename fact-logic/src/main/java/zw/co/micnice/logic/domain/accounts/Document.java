package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import zw.co.micnice.logic.domain.BaseIdEntity;
import zw.co.micnice.logic.domain.Beneficiary;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="document")
public class Document extends BaseIdEntity implements Serializable {

    private DocumentType documentType;
    private Date date;
    private Beneficiary beneficiary;
    private Boolean reversed;
    private Set<DocumentItem> documentItems= new HashSet<DocumentItem>();

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }
    
    @Enumerated(EnumType.STRING)
    public DocumentType getDocumentType() {
        return documentType;
    }
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }
    
    //To clarify
   
    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    @OneToMany(mappedBy = "document")
    public Set<DocumentItem> getDocumentItems() {
        return documentItems;
    }

    public void setDocumentItems(Set<DocumentItem> documentItems) {
        this.documentItems = documentItems;
    }
   
}
