package zw.co.micnice.logic.domain.accounts;


/**
 *
 * @author Matiashe Michael
 */
public enum Effect {
    
    DR("Debit"),
    CR("Credit");

    private Effect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    private final String name;
}
