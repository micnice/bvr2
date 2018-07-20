package zw.co.micnice.logic.domain.accounts;

/**
 *
 * @author Charles Chigoriwa
 */
public enum BalanceType {
    
    DR("Debit"),
    CR("Credit");

    private BalanceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    private final String name;
}
