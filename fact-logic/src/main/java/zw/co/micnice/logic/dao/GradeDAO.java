package zw.co.micnice.logic.dao;

import java.util.List;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.domain.GradeCategory;

/**
 *
 */
public interface GradeDAO extends IGenericDAO<Grade> {
    public List<GradeCategory> getGradeCategories();
}
