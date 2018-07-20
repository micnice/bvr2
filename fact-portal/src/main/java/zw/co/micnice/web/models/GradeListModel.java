/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.Grade;
import zw.co.micnice.logic.service.GradeService;

/**
 *
 * @author Michael Matiashe
 */
public class GradeListModel extends LoadableDetachableModel<List<Grade>> {

    private final GradeService gradeService;

    public GradeListModel(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Override
    protected List<Grade> load() {
        return gradeService.findAll();
    }
}
