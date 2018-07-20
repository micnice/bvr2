/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.BeneficiaryGradeDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.BeneficiaryGradeService;

/**
 *
 * @author Michael Matiashe
 */
@Service
@Transactional(readOnly = true)
public class BeneficiaryGradeServiceImpl implements BeneficiaryGradeService {

    @Autowired
    private BeneficiaryGradeDAO beneficiaryGradeDAO;

    @Transactional
    public BeneficiaryGrade save(BeneficiaryGrade beneficiaryGrade) {
        return beneficiaryGradeDAO.save(beneficiaryGrade);
    }

    public List<BeneficiaryGrade> findAll() {
        return beneficiaryGradeDAO.findAll();
    }

    public BeneficiaryGrade get(Long id) {
        return beneficiaryGradeDAO.get(id);
    }

    public void setBeneficiaryGradeDAO(BeneficiaryGradeDAO beneficiaryGradeDAO) {
        this.beneficiaryGradeDAO = beneficiaryGradeDAO;
    }

    public List<BeneficiaryGrade> getActiveGrades(Beneficiary beneficiary) {
        return beneficiaryGradeDAO.getActiveGrades(beneficiary);
    }

    public Long getTotalNumberOfPeople(Beneficiary beneficiary, Grade grade) {
        return beneficiaryGradeDAO.getTotalNumberOfPeople(beneficiary, grade);
    }

    public List<BeneficiaryGrade> getSummedBeneficiaryGrades() {
        return beneficiaryGradeDAO.getSummedBeneficiaryGrades();
    }

    public BeneficiaryGrade findByBeneficiaryAndGrade(Beneficiary beneficiary, Grade grade) {
        return beneficiaryGradeDAO.findByBeneficiaryAndGrade(beneficiary, grade);
    }
}
