package zw.co.micnice.logic.process.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.BeneficiaryGrade;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.DocumentItem;
import zw.co.micnice.logic.domain.accounts.DocumentType;
import zw.co.micnice.logic.domain.accounts.Effect;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;
import zw.co.micnice.logic.domain.accounts.TransactionComponent;
import zw.co.micnice.logic.domain.accounts.TransactionHeader;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.process.InvoiceProcess;
import zw.co.micnice.logic.service.BeneficiaryGradeService;
import zw.co.micnice.logic.service.ContributionService;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.logic.service.accounts.AccountsParametersService;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.logic.service.accounts.DocumentItemService;
import zw.co.micnice.logic.service.accounts.DocumentService;
import zw.co.micnice.logic.service.accounts.ReceiptItemService;
import zw.co.micnice.logic.service.accounts.TransactionComponentService;
import zw.co.micnice.logic.service.accounts.TransactionHeaderService;
import zw.co.micnice.logic.utils.CouncilException;

/**
 *
 * @author Michael Matishe
 */
@Service
public class InvoiceProcessImpl implements InvoiceProcess {

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
    private BeneficiaryGradeService beneficiaryGradeService;
    @Autowired
    private ReceiptItemService receiptItemService;
     @Autowired
     ContributionService contributionService;

    @Transactional
    public void invoice(Beneficiary beneficiary, List<BeneficiaryGrade> beneficiaryGrades) {
        TransactionType invoiceTransactionType = this.accountsParametersService.get().getInvoiceTransactionType();
        Document document = saveInvoiceDocument(beneficiary, beneficiaryGrades);
        for (BeneficiaryGrade beneficiaryGrade : beneficiaryGrades) {
            beneficiaryGrade = this.beneficiaryGradeService.get(beneficiaryGrade.getId());
            
            Account salesAccount = beneficiaryGrade.getGrade().getAccount();
            processCommonInvoiceOperations(invoiceTransactionType, salesAccount, beneficiaryGrade, BigDecimal.ZERO, BigDecimal.ZERO,document);
        }
    }
    
    @Transactional
    public DebtComponent invoice(Beneficiary beneficiary, BeneficiaryGrade beneficiaryGrade) {
        TransactionType invoiceTransactionType = this.accountsParametersService.get().getInvoiceTransactionType();
        Document document = saveInvoiceDocument(beneficiaryGrade.getBeneficiary(), beneficiaryGrade);
        beneficiaryGrade = this.beneficiaryGradeService.get(beneficiaryGrade.getId());
        BigDecimal iniBigDecimal= new BigDecimal('0');// = beneficiaryGrade.getBeneficiary().getAccount().getBalance();
        BigDecimal price = beneficiaryGrade.getTotalBasic();
        Account salesAccount = beneficiaryGrade.getGrade().getAccount();
        DebtComponent debtComponent = processCommonInvoiceOperations(invoiceTransactionType, salesAccount, beneficiaryGrade, price, iniBigDecimal, document);
        return debtComponent;
        
    }

    private DebtComponent processCommonInvoiceOperations(TransactionType invoiceTransactionType, Account salesAccount, BeneficiaryGrade beneficiaryGrade, BigDecimal price, BigDecimal initialBalance, Document document) {

        //Save new balances
        saveNewBalances(invoiceTransactionType, salesAccount, beneficiaryGrade.getBeneficiary(), price);

        //Post now to effect double entry
        DebtComponent debtComponent = post(salesAccount, invoiceTransactionType, price, beneficiaryGrade);
        debtComponent.setDocument(document);
        alterDebtComponentRemainingBalance(debtComponent, initialBalance);

        return debtComponent;
    }

    private DebtComponent post(Account salesAccount, TransactionType invoiceTransactionType, BigDecimal price, BeneficiaryGrade beneficiaryGrade) {

        Account drAccount = invoiceTransactionType.getDrLedger();

        TransactionHeader transactionHeader = postTransactionHeader(invoiceTransactionType);

//        postTransactionComponent(transactionHeader, beneficiaryGrade.getBeneficiary().getAccount(), price, new Date());

        postTransactionComponent(transactionHeader, drAccount, price, new Date());

        TransactionComponent salesTransactionComponent = postTransactionComponent(transactionHeader, salesAccount, new BigDecimal("-1").multiply(price), new Date());

        //Done with posting but let us mark the sales tran component for debt allocation
        return saveDebtComponent(salesTransactionComponent, price, new Account(), beneficiaryGrade);

    }

    private TransactionHeader postTransactionHeader(TransactionType invoiceTransactionType) {
        TransactionHeader transactionHeader = new TransactionHeader();
        transactionHeader.setDueDate(new Date());
        transactionHeader.setTransactionType(invoiceTransactionType);
        transactionHeader.setDescription(invoiceTransactionType.getDescription());
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

    private DebtComponent saveDebtComponent(TransactionComponent salesTransactionComponent, BigDecimal amount, Account personalAccount, BeneficiaryGrade beneficiaryGrade) {
        DebtComponent debtComponent = new DebtComponent();
        debtComponent.setRemainingBalance(amount);
        debtComponent.setTransactionComponent(salesTransactionComponent);
        debtComponent.setAccount(personalAccount);
        debtComponent.setBeneficiaryGrade(beneficiaryGrade);
        debtComponent.setDateCreated(new Date());
        debtComponentService.save(debtComponent);
        return debtComponent;
    }

    public void saveNewBalances(TransactionType invoiceTransactionType, Account salesAccount, Beneficiary customerAccount, BigDecimal amount) {
        if (salesAccount == null) {
            throw new CouncilException("Contact Administrator - Sale Account not set");
        }
        saveBeneficiaryNewBalance(customerAccount, invoiceTransactionType, amount);
        Account drAccount = accountService.get(invoiceTransactionType.getDrLedger().getId());
        drAccount.debit(amount);
        accountService.save(drAccount);
        Account crAccount = accountService.get(invoiceTransactionType.getCrLedger().getId());
        crAccount.credit(amount);
        accountService.save(crAccount);
        Account refreshedSalesAccount=accountService.get(salesAccount.getId());
        refreshedSalesAccount.credit(amount);
        accountService.save(refreshedSalesAccount);
    }

    private void saveBeneficiaryNewBalance(Beneficiary customer, TransactionType invoiceTransactionType, BigDecimal price) {
        if (invoiceTransactionType == null) {
            throw new CouncilException("Invoice transaction type not set up !!!");
        }

        if (invoiceTransactionType.getEffect().equals(Effect.DR)) {
            Account account  = accountService.get(new Account().getId());
            account.debit(price);
            accountService.save(account);
        } else {
            throw new CouncilException("Invalid effect on invoice transaction type");
        }

    }

    private Document saveInvoiceDocument(Beneficiary beneficiary, List<BeneficiaryGrade> beneficiaryGrades) {
        Document document = new Document();
        document.setBeneficiary(beneficiary);
        document.setDate(new Date());
        document.setDocumentType(DocumentType.INVOICE);
        
        for (BeneficiaryGrade  beneficiaryGrade: beneficiaryGrades) {
        DocumentItem documentItem = new DocumentItem();
        documentItem.setAccount(beneficiaryGrade.getGrade().getAccount());
        documentItem.setDocument(document);
        documentItem.setPrice(beneficiaryGrade.getTotalBasic());
        documentItem.setQuantity(1);
        documentItemService.save(documentItem);
        document.getDocumentItems().add(documentItem);
        }
        documentService.save(document);
        return document;
    }
    
    private Document saveInvoiceDocument(Beneficiary beneficiary, BeneficiaryGrade beneficiaryGrade) {
        Document document = new Document();
        document.setBeneficiary(beneficiary);
        document.setDate(new Date());
        document.setDocumentType(DocumentType.INVOICE);
        documentService.save(document);
        DocumentItem documentItem = new DocumentItem();
        documentItem.setAccount(beneficiaryGrade.getGrade().getAccount());
        documentItem.setDocument(document);
        documentItem.setPrice(beneficiaryGrade.getTotalBasic());
        documentItem.setQuantity(1);
        documentItemService.save(documentItem);
        return document;
    }

    private void saveInvoiceDocument(BeneficiaryGrade beneficiaryGrade, Document document, List<DocumentItem> documentItems) {
        document.setBeneficiary(beneficiaryGrade.getBeneficiary());
        document.setDate(new Date());
        document.setDocumentType(DocumentType.INVOICE);
        documentService.save(document);

        for (DocumentItem documentItem : documentItems) {
            documentItem.setDocument(document);
            documentItemService.save(documentItem);
        }
    }

    private ReceiptItem alterDebtComponentRemainingBalance(DebtComponent debtComponent, BigDecimal initialBalance) {
        
        ReceiptItem receiptItem= null;
        if (initialBalance.compareTo(new BigDecimal("0")) == -1) {
            int compareResult = initialBalance.multiply(new BigDecimal("-1")).compareTo(debtComponent.getRemainingBalance());
            
            if (compareResult >= 0) {
                receiptItem = new ReceiptItem();
                receiptItem.setAmount(debtComponent.getRemainingBalance());
                receiptItem.setDebtComponent(debtComponent);
                receiptItemService.save(receiptItem);
                debtComponent.setRemainingBalance(new BigDecimal("0"));
            } else if ((compareResult == -1)) {
                receiptItem = new ReceiptItem();
                BigDecimal amount = debtComponent.getRemainingBalance();
                BigDecimal remainingBalance = debtComponent.getRemainingBalance().subtract(initialBalance.multiply(new BigDecimal("-1")));
                debtComponent.setRemainingBalance(remainingBalance);
                receiptItem.setAmount(amount.subtract(remainingBalance));
                receiptItem.setDebtComponent(debtComponent);
                receiptItemService.save(receiptItem);

            }

            debtComponentService.save(debtComponent);
            
        }
        
        return receiptItem;
    }
}
