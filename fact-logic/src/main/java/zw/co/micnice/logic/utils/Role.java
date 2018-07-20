package zw.co.micnice.logic.utils;

/**
 *
 * @author Clive Gurure
 */
public enum Role {

    REGISTRAR("Registrar"),
    DEPUTY_REGISTRAR("Deputy Registrar"),
    FINANCIAL_ADMIN_EXECUTIVE("Financial AdminExecutive"),
    EDUCATION_OFFICER("Education Officer"),
    REGISTRY_SUPERVISOR("Registry Supervisor"),
    REGISTRY_CLERK("Registry Clerk"),
    DATA_CAPTURE_CLERK("Data Capture Clerk"),
    CERTIFICATION_CLERK("Certification Clerk"),
    ACCOUNTS_OFFICER("Accounts Officer"),
    BOOKKEEPER("Book Keeper"),
    SYSTEM_ADMINISTRATOR("Systems Administrator"),
    IT_OFFICER("IT Officer");

    private Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
    
    

    private final String roleName;
}
