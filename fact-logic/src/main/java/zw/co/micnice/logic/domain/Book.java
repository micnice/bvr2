package zw.co.micnice.logic.domain;


/**
 *
 * @author Matiashe Michael
 */
public enum Book {
    
    CB("Cash Book"),
    SL("Sales Ledger");

    private Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    private final String name;
}
