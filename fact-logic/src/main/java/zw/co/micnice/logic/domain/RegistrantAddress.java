package zw.co.micnice.logic.domain;

import java.io.Serializable;
import javax.persistence.*;
import zw.co.micnice.logic.utils.CaseUtil;

/**
 *
 * @author Constance Mabaso
 */
@Entity
@Table(name="registrantaddress")
public class RegistrantAddress extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private City city;
    private String address1;
    private String address2;
    private Boolean status;
    private AddressType addressType;
    private Registrant registrant;

    public RegistrantAddress() {
    }

    @ManyToOne(optional = true)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Basic(optional = true)
    @Column(name = "address1", nullable = true, length = 255)
    public String getAddress1() {
        return CaseUtil.upperCase(address1);
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic(optional = true)
    @Column(name = "address2", nullable = true, length = 50)
    public String getAddress2() {
        return CaseUtil.upperCase(address2);
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Transient
    public String getPreferred() {
        return status ? "Active" : "Inactive";
    }

    @Basic(optional = true)
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @ManyToOne(optional = true)
    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    
    @ManyToOne
    public Registrant getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Registrant registrant) {
        this.registrant = registrant;
    }



    @Transient
    public String getFullAddress() {
        return getAddress1() + " " + getAddress2() + " " + city;
    }

    @Override
    public String toString() {
        return getAddress1() + " " + getAddress2() + " " + city;
    }
}
