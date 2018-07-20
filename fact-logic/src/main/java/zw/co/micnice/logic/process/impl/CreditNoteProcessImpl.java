package zw.co.micnice.logic.process.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentItem;
import zw.co.micnice.logic.domain.accounts.DocumentType;
import zw.co.micnice.logic.domain.accounts.Effect;
import zw.co.micnice.logic.domain.accounts.TransactionComponent;
import zw.co.micnice.logic.domain.accounts.TransactionHeader;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.process.CreditNoteProcess;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.logic.service.accounts.DocumentItemService;
import zw.co.micnice.logic.service.accounts.DocumentService;
import zw.co.micnice.logic.service.accounts.TransactionComponentService;
import zw.co.micnice.logic.service.accounts.TransactionHeaderService;
import zw.co.micnice.logic.utils.CouncilException;

/**
 *
 * @author Michael Matishe
 */
@Service
public class CreditNoteProcessImpl implements CreditNoteProcess {

    @Autowired
    private TransactionHeaderService transactionHeaderService;
    @Autowired
    private TransactionComponentService transactionComponentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentItemService documentItemService;
    @Autowired
    private DebtComponentService debtComponentService;
    @Autowired
    private AccountsParametersService accountsParametersService;
    @Autowired
    private BeneficiaryService beneficiaryService;

    @Transactional
    public void processCreditNote(Beneficiary beneficiary, List<DebtComponent> debtComponents) {
        TransactionType creditNoteTransactionType = this.accountsParametersService.get().getCreditNoteTransactionType();
        saveCreditNoteDocument(beneficiary, debtComponents);
        for (DebtComponent debtComponent : debtComponents) {
            beneficiary = this.beneficiaryService.get(beneficiary.getId());
            BigDecimal price = debtComponent.getRemainingBalance();           
            //Just Value
            Account salesAccount = debtComponent.getProduct().getSalesAccount();
            processCommonCreditNoteOperations(creditNoteTransactionType, salesAccount, beneficiary, price, debtComponent);
        }
    }

    private DebtComponent processCommonCreditNoteOperations(TransactionType creditNoteTransactionType, Account salesAccount, Beneficiary beneficiary, BigDecimal price, DebtComponent debtComponent) {

        //Save new balances
        saveNewBalances(creditNoteTransactionType, salesAccount, beneficiary, price);

        //Post now to effect double entry
        post(beneficiary, salesAccount, creditNoteTransactionType, price);
        debtComponent.setRemainingBalance(new BigDecimal("0"));
        debtComponentService.save(debtComponent);

        return debtComponent;
    }

    private void post(Beneficiary beneficiary, Account salesAccount, TransactionType creditNoteTransactionType, BigDecimal price) {

        Account drAccount = creditNoteTransactionType.getDrLedger();

        TransactionHeader transactionHeader = postTransactionHeader(creditNoteTransactionType);

//        postTransactionComponent(transactionHeader, beneficiary.getAccount(), price, new Date());

        postTransactionComponent(transactionHeader, drAccount, price, new Date());

        TransactionComponent creditNoteTransactionComponent = postTransactionComponent(transactionHeader, salesAccount, price, new Date());

    }

    private TransactionHeader postTransactionHeader(TransactionType creditNoteTransactionType) {
        TransactionHeader transactionHeader = new TransactionHeader();
        transactionHeader.setDueDate(new Date());
        transactionHeader.setTransactionType(creditNoteTransactionType);
        transactionHeader.setDescription(creditNoteTransactionType.getDescription());
        transactionHeader = transactionHeaderService.save(transactionHeader);
        return transactionHeader;
    }

    private TransactionComponent postTransactionComponent(TransactionHeader transactionHeader, Account account, BigDecimal amount, Date dueDate) {
        TransactionComponent transactionComponent = new TransactionComponent();
        transactionComponent.setTransactionHeader(transactionHeader);
        transactionComponent.setDueDate(dueDate);
        transactionComponent.setAccount(account);
        transactionComponent.setAmount(amount);
        transactionComponentService.save(transactionComponent);
        return transactionComponent;
    }

    private void saveNewBalances(TransactionType creditNoteTransactionType, Account salesAccount, Beneficiary beneficiary, BigDecimal amount) {
        saveCustomerNewBalance(beneficiary, creditNoteTransactionType, amount);
        Account crAccount = accountService.get(creditNoteTransactionType.getCrLedger().getId());
        crAccount.credit(amount);
        accountService.save(crAccount);
        Account drAccount = accountService.get(creditNoteTransactionType.getDrLedger().getId());
        drAccount.debit(amount);
        accountService.save(drAccount);
        salesAccount.debit(amount);
        accountService.save(salesAccount);

    }

    private void saveCustomerNewBalance(Beneficiary beneficiary, TransactionType creditNoteTransactionType, BigDecimal price) {
        if (creditNoteTransactionType == null) {
            throw new CouncilException("Credit Note transaction type not set up !!!");
        }

        if (creditNoteTransactionType.getEffect().equals(Effect.CR)) {
//            beneficiary.getAccount().credit(price);
//            accountService.save(beneficiary.getAccount());
        } else {
            throw new CouncilException("Invalid effect on Credit Note transaction type");
        }

    }

    private void saveCreditNoteDocument(Beneficiary beneficiary, List<DebtComponent> debtComponents) {
        Document document = new Document();
        document.setBeneficiary(beneficiary);
        document.setDate(new Date());
        document.setDocumentType(DocumentType.CREDIT_NOTE);
        
        for (DebtComponent debtComponent : debtComponents) {
        DocumentItem documentItem = new DocumentItem();
        documentItem.setAccount(debtComponent.getProduct().getSalesAccount());
        documentItem.setDocument(document);
        documentItem.setPrice(debtComponent.getRemainingBalance());
        documentItem.setProduct(debtComponent.getProduct());
        documentItem.setQuantity(1);
        documentItemService.save(documentItem);
        document.getDocumentItems().add(documentItem);
        }
        documentService.save(document);
    }

}
