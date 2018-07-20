package zw.co.micnice.logic.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.GradeDAO;
import zw.co.micnice.logic.dao.repo.GradeRepository;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.AccountType;
import zw.co.micnice.logic.domain.GradeCategory;
import static zw.co.micnice.logic.domain.GradeCategory.GRADED_WORKER_CATEGORY;
import static zw.co.micnice.logic.domain.GradeCategory.SKILLED_WORKER_CATEGORY;
import static zw.co.micnice.logic.domain.GradeCategory.SKILLED_WORKER_TRAINEE_CATEGORY;

/**
 *
 * @author Michael Matiashe
 */
@Repository
public class GradeDAOImpl implements GradeDAO {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade save(Grade grade) {
        grade.setAccount(new Account());
        grade.getAccount().setBalance(BigDecimal.ZERO);
        grade.getAccount().setAccountType(AccountType.SALES);
        grade.getAccount().setCode(UUID.randomUUID().toString());
        grade.getAccount().setName(grade.getFullName());
        grade.getAccount().setPersonalAccount(Boolean.FALSE);
        return gradeRepository.save(grade);
    }

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public Grade get(Long id) {
        return gradeRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param gradeRepository
     */
    public void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<GradeCategory> getGradeCategories() {
        return Arrays.asList(GRADED_WORKER_CATEGORY, SKILLED_WORKER_CATEGORY, SKILLED_WORKER_TRAINEE_CATEGORY);
    }
}
