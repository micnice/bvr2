/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao;

import java.util.List;
import java.util.Set;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.Grade;

/**
 *
 * @author Michael Matiashe
 */
public interface BeneficiaryGradeDAO extends IGenericDAO<BeneficiaryGrade>{
    public List<BeneficiaryGrade> getActiveGrades(Beneficiary beneficiary);
    public Long getTotalNumberOfPeople(Beneficiary beneficiary,Grade grade);
    public List<BeneficiaryGrade> getSummedBeneficiaryGrades();
    public BeneficiaryGrade findByBeneficiaryAndGrade(Beneficiary beneficiary,Grade grade);
}
