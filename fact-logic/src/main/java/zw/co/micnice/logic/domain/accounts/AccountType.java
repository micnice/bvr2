package zw.co.micnice.logic.domain.accounts;
import static zw.co.micnice.logic.domain.accounts.AccountTypeCategory.*;
import static zw.co.micnice.logic.domain.accounts.BalanceType.*;


/**
 *
 * @author Charles Chigoriwa
 */
public enum AccountType {
    
    
    /*Balance Sheet Category*/
    UNALLOCATED_BALANCE_SHEET(BALANCE_SHEET,null,"Unallocated Balance Sheet"),
    SHARE_CAPITAL(BALANCE_SHEET,CR,"Share Capital"),
    RETAINED_INCOME(BALANCE_SHEET,CR,"Retained Income"),
    SHAREHOLDERS_LOAN(BALANCE_SHEET,CR,"Shareholders Loan"),
    LONG_TERM_BORROWINGS(BALANCE_SHEET,CR,"Long term borrowings"),
    OTHER_LONG_TERM_LIABILITIES(BALANCE_SHEET,CR,"Other Long Term Liabilities"),
    FIXED_ASSETS(BALANCE_SHEET,DR,"Fixed Assets"),
    INVESTMENTS(BALANCE_SHEET,DR,"Investments"),
    OTHER_FIXED_ASSETS(BALANCE_SHEET,DR,"Other Fixed Assets"),
    INVENTORY(BALANCE_SHEET,DR,"Inventory"),
    ACCOUNTS_RECEIVABLE(BALANCE_SHEET,DR,"Accounts Receivable"),
    BANK(BALANCE_SHEET,DR,"Bank"),
    OTHER_CURRENT_ASSETS(BALANCE_SHEET,DR,"Other Current Assets"),
    ACCOUNTS_PAYABLE(BALANCE_SHEET,CR,"Accounts Payable"),
    TAXATION(BALANCE_SHEET,CR,"Taxation"),
    OTHER_CURRENT_LIABILITIES(BALANCE_SHEET,CR,"Other Current Liabilities"),
    /*Income Statement Category*/
    UNALLOCATED_INCOME_STATEMENT(INCOME_STATEMENT,CR,"Unallocated Income Statement"),
    SALES(INCOME_STATEMENT,CR,"Sales"),
    COST_OF_SALES(INCOME_STATEMENT,DR,"Cost of Sales"),
    OTHER_INCOME(INCOME_STATEMENT,CR,"Other Income"),
    EXPENSES(INCOME_STATEMENT,DR,"Expenses"),
    TAX(INCOME_STATEMENT,DR,"Tax"),
    DIVIDENDS(INCOME_STATEMENT,CR,"Dividends");

    
    

    private AccountType(AccountTypeCategory accountTypeCategory, BalanceType balanceType, String name) {
        this.accountTypeCategory = accountTypeCategory;
        this.balanceType = balanceType;
        this.name = name;
    }

    public AccountTypeCategory getAccountTypeCategory() {
        return accountTypeCategory;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public String getName() {
        return name;
    }
    
   
    private final AccountTypeCategory accountTypeCategory;
    private final BalanceType balanceType;
    private final String name;
    
}
