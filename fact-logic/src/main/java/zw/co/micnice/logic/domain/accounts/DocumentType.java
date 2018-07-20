package zw.co.micnice.logic.domain.accounts;

/**
 *
 * @author charlesc
 */

public enum DocumentType {
    INVOICE ("Invoice"),
    QUOTATION("Quotation"),
    ORDER("Order"),
    CREDIT_NOTE("Credit Note");
    
 private DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    private final String name;
}
