/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import zw.co.micnice.logic.domain.accounts.Account;

@Entity
@Table(name = "beneficiary")
public class Beneficiary extends BaseEntity implements Serializable {

    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private Gender gender;
    private Date dateOfBirth;
    private MaritalStatus maritalStatus;
    private LevelOfEducation levelOfEducation;
    private Date lmp;
    private Date dateIdentified;
    private User identifiedBy;
    private int parity;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Enumerated(EnumType.STRING)
    public LevelOfEducation getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(LevelOfEducation levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLmp() {
        return lmp;
    }

    public void setLmp(Date lmp) {
        this.lmp = lmp;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateIdentified() {
        return dateIdentified;
    }

    public void setDateIdentified(Date dateIdentified) {
        this.dateIdentified = dateIdentified;
    }

    @ManyToOne
    public User getIdentifiedBy() {
        return identifiedBy;
    }

    public void setIdentifiedBy(User identifiedBy) {
        this.identifiedBy = identifiedBy;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

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

    @Temporal(TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
