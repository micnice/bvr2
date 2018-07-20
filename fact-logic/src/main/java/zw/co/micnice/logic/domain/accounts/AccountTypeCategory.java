package zw.co.micnice.logic.domain.accounts;

/**
 *
 * @author Charles Chigoriwa
 */
public enum AccountTypeCategory {
    
    INCOME_STATEMENT("Income Statement"),
    BALANCE_SHEET("Balance Sheet");
    
    private AccountTypeCategory(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
    
    private final String name;
}
