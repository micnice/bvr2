package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.GradeDAO;
import zw.co.micnice.logic.service.GradeService;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.domain.GradeCategory;

/**
 *@author Michael Matiashe
 */
@Service
@Transactional(readOnly=true)
public class GradeServiceImpl implements GradeService{
    
    @Autowired
    private GradeDAO gradeDAO;
    

    @Transactional
    public Grade save(Grade addressType) {
       return gradeDAO.save(addressType);
    }

    public List<Grade> findAll() {
        return gradeDAO.findAll();
    }

    public Grade get(Long id) {
       return gradeDAO.get(id);
    }

    public void setGradeDAO(GradeDAO addressTypeDAO) {
        this.gradeDAO = addressTypeDAO;
    }

    public List<GradeCategory> getGradeCategories() {
        return gradeDAO.getGradeCategories();
    }
    
}
