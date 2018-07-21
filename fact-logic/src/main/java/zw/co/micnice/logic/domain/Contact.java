/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String contactDetail;
    private Boolean status = Boolean.TRUE;
    private ContactType contactType;
    private Beneficiary beneficiary;

    public Contact() {
    }

    @Basic(optional = false)
    @Column(name = "contact_detail", nullable = false, length = 255)
    public String getContactDetail() {
          return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }

    @Basic(optional = false)
    @Column(name = "Status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Transient
    public String getPreferred() {
        return status ? "Active" : "Inactive";
    }

    @ManyToOne
    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    @ManyToOne
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public String toString() {
        return getContactDetail();
    }
}