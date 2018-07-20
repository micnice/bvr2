/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.service.accounts.TransactionTypeService;

/**
 *
 * @author Michael Matiashe
 */
public class BankAccountsModel extends LoadableDetachableModel<List<TransactionType>> {

    private final TransactionTypeService transactionTypeService;

    public BankAccountsModel(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    @Override
    protected List<TransactionType> load() {
        return transactionTypeService.findBankAccounts();
    }
}
