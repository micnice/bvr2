/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.Grade;

/**
 *
 * @author Michael Matiashe
 */
public interface BeneficiaryGradeRepository extends CrudRepository<BeneficiaryGrade, Long>{
     public List<BeneficiaryGrade> findAll();
     public BeneficiaryGrade findByBeneficiaryAndGrade(Beneficiary beneficiary,Grade grade);
}
