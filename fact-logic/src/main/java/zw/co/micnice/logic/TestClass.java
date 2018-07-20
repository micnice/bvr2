/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.BeneficiaryGradeService;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.GradeService;

/**
 * Test class to be removed
 *
 * @author Clive Gurure
 */
@Repository
public class TestClass {

    @Autowired
    private BeneficiaryGradeService beneficiaryGradeService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private BeneficiaryService beneficiaryService;

    public void printGradeDetails() {
        DecimalFormat df = new DecimalFormat("#.#");
        List<Grade> grades = gradeService.findAll();
        List<Beneficiary> companiesList = beneficiaryService.findAll();
        for(Beneficiary beneficiary : companiesList){
           
            for(Grade grade : grades){
                
//                System.out.println("Beneficiary Name: " + beneficiary.getBeneficiaryName());
                System.out.println("Grade: " + grade.getFullName());
                System.out.println("Number Of Employees: " + beneficiaryGradeService.getTotalNumberOfPeople(beneficiary, grade));
                System.out.println("Total Basic Pay: " + grade.getMonthlyRate());
                System.out.println("Employee Contributions: " + (grade.getMonthlyRate() == null ? "No Monthly Rate" : df.format(grade.getMonthlyRate() * 0.025)));
                System.out.println("Employer Contributions: " + (grade.getMonthlyRate() == null ? "No Monthly Rate" : df.format(grade.getMonthlyRate() * 0.025)));
                System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }
    
    public void printBasicPay() {
        List<BeneficiaryGrade>  beneficiaryGrades = beneficiaryGradeService.findAll();
        for(BeneficiaryGrade beneficiaryGrade : beneficiaryGrades){
            System.out.println("Basic Pay=" + beneficiaryGrade.getTotalBasic());
        }
    }
}
