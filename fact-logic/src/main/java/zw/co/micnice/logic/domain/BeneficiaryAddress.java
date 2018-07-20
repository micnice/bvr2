/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="beneficiaryaddress")
public class BeneficiaryAddress extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String addressDetail;
    private Boolean status = Boolean.TRUE;
    private AddressType addressType;
    private Beneficiary beneficiary;

    public BeneficiaryAddress() {
    }

    @Basic(optional = false)
    @Column(name = "address_detail", nullable = false, length = 255)
    public String getAddressDetail() {
          return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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
    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
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
        return getAddressDetail();
    }
}
