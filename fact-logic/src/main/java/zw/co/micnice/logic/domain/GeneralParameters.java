package zw.co.micnice.logic.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "generalparameters")
public class GeneralParameters extends BaseIdEntity implements Serializable {

    private Double employerContribution = 0D;
    private Double employeeContribution = 0D;

    public Double getEmployerContribution() {
        return employerContribution;
    }

    public void setEmployerContribution(Double employerContribution) {
        this.employerContribution = employerContribution;
    }

    public Double getEmployeeContribution() {
        return employeeContribution;
    }

    public void setEmployeeContribution(Double employeeContribution) {
        this.employeeContribution = employeeContribution;
    }
}
