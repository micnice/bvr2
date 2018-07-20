/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.Grade;

/**
 *
 * @author Michael Matiashe
 */
public interface BeneficiaryGradeService extends IGenericService<BeneficiaryGrade> {

    public List<BeneficiaryGrade> getActiveGrades(Beneficiary beneficiary);
    public Long getTotalNumberOfPeople(Beneficiary beneficiary,Grade grade);
    public List<BeneficiaryGrade> getSummedBeneficiaryGrades();
    public BeneficiaryGrade findByBeneficiaryAndGrade(Beneficiary beneficiary,Grade grade);
}
