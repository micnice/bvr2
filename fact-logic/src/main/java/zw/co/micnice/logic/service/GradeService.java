package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.domain.GradeCategory;

/**
 *
 * @author Matiashe Michael
 */
public interface GradeService extends IGenericService<Grade> {
    public List<GradeCategory> getGradeCategories();
}
