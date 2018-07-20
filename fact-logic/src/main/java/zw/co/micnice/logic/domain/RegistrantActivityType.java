package zw.co.micnice.logic.domain;

/**
 *
 * @author Charles Chigoriwa
 */
public enum RegistrantActivityType {
    
    REGISTRATION("Registration"),
    EXAM_REGISTRATION("Exam Registration"),
    RENEWAL("Renewal"),
    DEREGISTRATION("Deregistration"),
    CHANGE_OF_NAME("Change of Name");

    private RegistrantActivityType(String name) {
        this.name = name;
    }
    private final String name;

    public String getName() {
        return name;
    }
    
    
}
