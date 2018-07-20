/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name = "beneficiarygrade")
public class BeneficiaryGrade extends BaseIdEntity implements Serializable, Comparable<BeneficiaryGrade> {

    private static final long serialVersionUID = 1L;
    private Long numberOfEmployees;
    private Boolean status = Boolean.TRUE;
    private Grade grade;
    private Beneficiary beneficiary;
    private BigDecimal totalBasic = new BigDecimal("0");

    public BigDecimal getTotalBasic() {
        return totalBasic;
    }

    public void setTotalBasic(BigDecimal totalBasic) {
        this.totalBasic = totalBasic;
    }
    
    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @ManyToOne
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @ManyToOne
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public BeneficiaryGrade() {
    }

    @Basic(optional = false)
    @Column(name = "Status", nullable = false)
    public Boolean getStatus() {
        if (status == null) {
            return Boolean.FALSE;
        } else {
            return status;
        }
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Transient
    public String getPreferred() {
        return status ? "Active" : "Inactive";
    }

    public int compareTo(BeneficiaryGrade otherBeneficiaryGrade) {
        //TODO : Verify this is working as planned
        return this.getBeneficiary().getFirstName().compareTo(otherBeneficiaryGrade.getBeneficiary().getFirstName())
                + this.getGrade().getFullName().compareTo(otherBeneficiaryGrade.getGrade().getFullName());

    }
}
