/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.io.Serializable;

public enum GradeCategory implements Serializable {
    
    SKILLED_WORKER_CATEGORY("SKILLED WORKER CATEGORY"),
    GRADED_WORKER_CATEGORY("GRADED WORKER CATEGORY"),
    SKILLED_WORKER_TRAINEE_CATEGORY("GRADED WORKER TRAINEE CATEGORY");
    
    private GradeCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }
       

    private final String categoryName;
}
