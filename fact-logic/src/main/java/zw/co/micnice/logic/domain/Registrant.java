package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.utils.CaseUtil;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@Entity
@Table(name = "registrant")
public class Registrant extends BaseIdEntity implements Serializable {
   
    boolean order = false;
    private String firstname;
    private String lastname;
    private String middlename;
    private Date birthDate;
    private String gender;
    private String idNumber;
    private String registrationNumber;
    private MaritalStatus maritalStatus;
    private Title title;
    private Citizenship citizenship;
    private Boolean dead = Boolean.FALSE;
    private String placeOfBirth;
    private Customer customerAccount;
    private IdentificationType identificationType;
    private Set<SupportDocument> supportDocuments = new HashSet<SupportDocument>();
    private String activeStatus;
    private String activeRegisters;
    public static String ACTIVE = "ACTIVE";
    public static String INACTIVE = "INACTIVE";
    private Set<Contact> registrantContacts;
    private Set<RegistrantAddress> registrantAddresses;

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @OneToMany(mappedBy = "registrant")
    public Set<SupportDocument> getSupportDocuments() {
        return supportDocuments;
    }

    public void setSupportDocuments(Set<SupportDocument> supportDocuments) {
        this.supportDocuments = supportDocuments;
    }

    public String getFirstname() {
        return CaseUtil.upperCase(firstname);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return CaseUtil.upperCase(lastname);
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @ManyToOne
    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    public String getMiddlename() {
        if (middlename == null) {
            return "";
        }
        return CaseUtil.upperCase(middlename);
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(unique = true)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Column(unique = true)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @ManyToOne
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @ManyToOne
    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    @OneToOne
    @Cascade(CascadeType.PERSIST)
    public Customer getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Customer customerAccount) {
        this.customerAccount = customerAccount;
    }

    @Transient
    public int getIntegerValueOfAge() {
        if (birthDate == null) {
            return 0;
        }

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);

        Calendar todayCalendar = Calendar.getInstance();

        int age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (todayCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)) {
            age--;
        } else if (todayCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
                && todayCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }
    public Boolean getDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    @Transient
    public String getFullname() {
        //Depenidng on Council - Lastname Firstname Middlename
        if (order) {
            return getLastname() + " " + getFirstname() + " " + getMiddlename();
        } else {
            //Depenidng on Council - Firstname Middlename Lastname 
            return getFirstname() + " " + getMiddlename() + " " + getLastname();
        }
    }

    @Transient //Defined for Zimbabwe with Retirement Age 65
    public String getStatusDueForRetirement() {
        if (getIntegerValueOfAge() > 65) {
            Integer years = getIntegerValueOfAge() - 65;
            return "Due for Retirement";
        } else {
            Integer years = 65 - getIntegerValueOfAge();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, years);
            return "Due for Retirement in " + years + " years. Year " + cal.get(Calendar.YEAR);
        }
    }

    @Transient
    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Transient
    public String getActiveRegisters() {
        return activeRegisters;
    }

    public void setActiveRegisters(String activeRegisters) {
        this.activeRegisters = activeRegisters;
    }

    @OneToMany(mappedBy = "registrant")
    public Set<Contact> getRegistrantContacts() {
        return registrantContacts;
    }

    public void setRegistrantContacts(Set<Contact> registrantContacts) {
        this.registrantContacts = registrantContacts;
    }

    @OneToMany(mappedBy = "registrant")
    public Set<RegistrantAddress> getRegistrantAddresses() {
        return registrantAddresses;
    }

    public void setRegistrantAddresses(Set<RegistrantAddress> registrantAddresses) {
        this.registrantAddresses = registrantAddresses;
    }
}