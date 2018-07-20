/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.BeneficiaryGradeDAO;
import zw.co.micnice.logic.dao.repo.BeneficiaryGradeRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.GradeService;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class BeneficiaryGradeDAOImpl implements BeneficiaryGradeDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private BeneficiaryGradeRepository beneficiaryGradeRepository;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private BeneficiaryService beneficiaryService;

    public BeneficiaryGrade save(BeneficiaryGrade beneficiaryGrade) {
        return beneficiaryGradeRepository.save(beneficiaryGrade);
    }

    public List<BeneficiaryGrade> findAll() {
        return beneficiaryGradeRepository.findAll();
    }

    public BeneficiaryGrade get(Long id) {
        return beneficiaryGradeRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param beneficiaryGradeRepository
     */
    public void setBeneficiaryGradeRepository(BeneficiaryGradeRepository beneficiaryGradeRepository) {
        this.beneficiaryGradeRepository = beneficiaryGradeRepository;
    }

    public List<BeneficiaryGrade> getActiveGrades(Beneficiary beneficiary) {
        return entityManager.createQuery("select c from BeneficiaryGrade c where c.beneficiary=:beneficiary and c.status=:status").setParameter("beneficiary", beneficiary).setParameter("status", Boolean.TRUE).getResultList();
    }

    public Long getTotalNumberOfPeople(Beneficiary beneficiary, Grade grade) {
        Long numberOfEmployees = (Long) entityManager.createQuery("select SUM(c.numberOfEmployees) from BeneficiaryGrade c where c.grade=:grade AND "
                + "c.beneficiary=:beneficiary").setParameter("grade", grade).setParameter("beneficiary", beneficiary).getSingleResult();
        if (numberOfEmployees == null) {
            return 0L;
        }
        return numberOfEmployees;
    }

    public BeneficiaryGrade findByBeneficiaryAndGrade(Beneficiary beneficiary, Grade grade) {
        return beneficiaryGradeRepository.findByBeneficiaryAndGrade(beneficiary, grade);
    }

    public List<BeneficiaryGrade> getSummedBeneficiaryGrades() {

        BeneficiaryGrade beneficiaryGrade;
        Long numberOfPeople;
        BigDecimal totalBasicPay = new BigDecimal("0");
        List<Grade> grades = gradeService.findAll();
        List<Beneficiary> companiesList = beneficiaryService.findAll();
        List<BeneficiaryGrade> beneficiaryGrades = new ArrayList<BeneficiaryGrade>();

        for (Beneficiary beneficiary : companiesList) {

            for (Grade grade : grades) {

                numberOfPeople = getTotalNumberOfPeople(beneficiary, grade);
                if (numberOfPeople > 0) {

                    totalBasicPay = findByBeneficiaryAndGrade(beneficiary, grade).getTotalBasic();
                    beneficiaryGrade = new BeneficiaryGrade();
                    beneficiaryGrade.setBeneficiary(beneficiary);
                    beneficiaryGrade.setGrade(grade);
                    beneficiaryGrade.setNumberOfEmployees(numberOfPeople);
                    beneficiaryGrade.setTotalBasic(totalBasicPay);
                    beneficiaryGrades.add(beneficiaryGrade);
                }
            }
        }

        return beneficiaryGrades;
    }
}
