package zw.co.micnice.logic.process.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Allocation;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.PaymentDetails;
import zw.co.micnice.logic.domain.accounts.ReceiptHeader;
import zw.co.micnice.logic.domain.accounts.ReceiptItem;
import zw.co.micnice.logic.domain.accounts.TransactionComponent;
import zw.co.micnice.logic.domain.accounts.TransactionHeader;
import zw.co.micnice.logic.domain.accounts.TransactionType;
import zw.co.micnice.logic.process.PaymentProcess;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.logic.service.accounts.AllocationService;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.logic.service.accounts.PaymentDetailsService;
import zw.co.micnice.logic.service.accounts.ReceiptHeaderService;
import zw.co.micnice.logic.service.accounts.ReceiptItemService;
import zw.co.micnice.logic.service.accounts.TransactionComponentService;
import zw.co.micnice.logic.service.accounts.TransactionHeaderService;
import zw.co.micnice.logic.utils.CouncilException;

/**
 *
 * @author Michael Matiashe
 */
@Service
public class PaymentProcessImpl implements PaymentProcess {

    @Autowired
    private ReceiptHeaderService receiptHeaderService;
    @Autowired
    private ReceiptItemService receiptItemService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private DebtComponentService debtComponentService;
    @Autowired
    private AllocationService allocationService;
    @Autowired
    private TransactionHeaderService transactionHeaderService;
    @Autowired
    private TransactionComponentService transactionComponentService;
    @Autowired
    private PaymentDetailsService paymentDetailsService;
    @Autowired
    private BeneficiaryService beneficiaryService;

    @Transactional()
    public PaymentDetails processPayment(PaymentDetails paymentDetails, List<ReceiptItem> receiptItems) {

        if (!paymentDetails.getPaymentMethod().getReceiptNumberRequired()) {
            validateDateOfPayment(paymentDetails.getDateOfPayment());
        }


        if (!paymentDetails.getPaymentMethod().getDepositDateRequired()) {
            validateAmountTendered(paymentDetails, receiptItems);
        }

        ReceiptHeader receiptHeader = paymentDetails.getReceiptHeader();

        int zeroOrNegativeCompare = receiptHeader.getTotalAmount().compareTo(new BigDecimal("0"));

        if (zeroOrNegativeCompare == 0 || zeroOrNegativeCompare == -1) {
            throw new CouncilException("Amount paid must be greater than zero");
        }

        if (!paymentDetails.getPaymentMethod().getReceiptNumberRequired()) {
            receiptHeader.setGeneratedReceiptNumber(null);
        }

        TransactionType receiptTranType = paymentDetails.getTransactionHeader().getTransactionType();

        if (receiptTranType == null) {
            throw new CouncilException("Receipt transaction type must not be null");
        }

        BigDecimal totalReceiptItemAmount = new BigDecimal("0");
        BigDecimal totalReceiptItemRemainingBalance = new BigDecimal("0");
        for (ReceiptItem receiptItem : receiptItems) {
            if (receiptItem.getDebtComponent().getRemainingBalance().compareTo(receiptItem.getAmount()) == -1) {
                throw new CouncilException("One of the receipt items has an amount greater than remaining balance");
            }

            zeroOrNegativeCompare = receiptItem.getAmount().compareTo(new BigDecimal("0"));

            if (zeroOrNegativeCompare == 0 || zeroOrNegativeCompare == -1) {
                throw new CouncilException("Receipt item amount must be greater than zero");
            }

            receiptItem.setReceiptHeader(receiptHeader);
            totalReceiptItemAmount = totalReceiptItemAmount.add(receiptItem.getAmount());
            totalReceiptItemRemainingBalance = totalReceiptItemRemainingBalance.add(receiptItem.getDebtComponent().getRemainingBalance());

        }

        if (receiptHeader.getTotalAmount().compareTo(totalReceiptItemAmount) == -1) {
            throw new CouncilException("Total amount paid can not be less than total receipt item amount");
        }

        List<DebtComponent> debtComponents = debtComponentService.getDebtComponents(new Account());
        if (!debtComponents.isEmpty()) {
            if (debtComponents.size() > receiptItems.size() && receiptHeader.getTotalAmount().compareTo(totalReceiptItemAmount) == 1) {
                throw new CouncilException("Select other items to pay");

            }
        }
        int compare = receiptHeader.getTotalAmount().compareTo(totalReceiptItemRemainingBalance);
        for (ReceiptItem receiptItem : receiptItems) {
            if (compare == 0 || compare == 1) {
                if (receiptItem.getAmount().compareTo(receiptItem.getDebtComponent().getRemainingBalance()) != 0) {
                    throw new CouncilException("Receipt amount must be fully paid");
                }
            }
        }

        Beneficiary beneficiary = beneficiaryService.get(paymentDetails.getBeneficiary().getId());

        Account beneficiaryAccount = new Account();

        //in case user is overpaying but with some remaining debts not picked
        if (compare == 1) {
            if (receiptHeader.getTotalAmount().compareTo(beneficiaryAccount.getBalance()) == -1) {
                throw new CouncilException("Overpayment while still having other unpaid debt components is not allowed. Please add other debt components.");
            }
        }

        receiptHeader.setDate(new Date());
        receiptHeader.setTransactionType(receiptTranType);
        receiptHeader.setPaymentDetails(paymentDetails);
        receiptHeader.setCreatedBy(paymentDetails.getCreatedBy());
        receiptHeader = receiptHeaderService.save(receiptHeader);

        BigDecimal totalAmount = receiptHeader.getTotalAmount();

        beneficiaryAccount.credit(totalAmount);
        accountService.save(beneficiaryAccount);
        // account is refreshed
        Account bankAccount = receiptTranType.getDrLedger();
        bankAccount = accountService.get(bankAccount.getId());
        bankAccount.debit(totalAmount);
        accountService.save(bankAccount);
        // account is refreshed
        Account creditAccount = receiptTranType.getCrLedger();
        creditAccount = accountService.get(creditAccount.getId());
        creditAccount.credit(totalAmount);
        accountService.save(creditAccount);

        TransactionHeader transactionHeader = paymentDetails.getTransactionHeader();
        transactionHeader.setTransactionType(receiptTranType);
        transactionHeader.setDueDate(new Date());
        transactionHeader.setDescription(receiptTranType.getDescription());
        transactionHeaderService.save(transactionHeader);

        TransactionComponent transactionComponent = new TransactionComponent();
        transactionComponent.setAmount(totalAmount);
        transactionComponent.setTransactionHeader(transactionHeader);
        transactionComponent.setAccount(bankAccount);
        transactionComponent.setDueDate(new Date());
        transactionComponentService.save(transactionComponent);

        transactionComponent = new TransactionComponent();
        transactionComponent.setAccount(beneficiaryAccount);
        transactionComponent.setAmount(totalAmount.multiply(new BigDecimal("-1")));
        transactionComponent.setDueDate(new Date());
        transactionComponent.setTransactionHeader(transactionHeader);
        transactionComponentService.save(transactionComponent);

        transactionComponent = new TransactionComponent();
        transactionComponent.setAccount(creditAccount);
        transactionComponent.setAmount(totalAmount.multiply(new BigDecimal("-1")));
        transactionComponent.setDueDate(new Date());
        transactionComponent.setTransactionHeader(transactionHeader);
        transactionComponentService.save(transactionComponent);

        for (ReceiptItem receiptItem : receiptItems) {
            receiptItem.setReceiptHeader(receiptHeader);
            receiptItem = receiptItemService.save(receiptItem);
            receiptHeader.getReceiptItems().add(receiptItem);
            DebtComponent debtComponent = receiptItem.getDebtComponent();
            BigDecimal amount = receiptItem.getAmount();
            debtComponent.reduceRemainingBalance(amount);
            debtComponentService.save(debtComponent);

            Allocation allocation = new Allocation();
            allocation.setDebtTransactionComponent(debtComponent.getTransactionComponent());
            allocation.setPaymentTransactionComponent(transactionComponent);
            allocation.setAmount(amount);
            allocationService.save(allocation);
        }
        paymentDetails.setReceiptHeader(receiptHeader);
        PaymentDetails details = paymentDetailsService.save(paymentDetails);



        details.setBankAccount(details.getTransactionHeader().getTransactionType().getDrLedger());
        receiptHeader.setPaymentDetails(details);
        receiptHeaderService.save(receiptHeader);

        return details;

    }

    public void validateDateOfPayment(Date date) {
        if (date.after(new Date())) {
            throw new CouncilException("Deposit date must not be a future date");
        }
    }

    public void validateDateOfDeposit(Date date) {

        if (date.after(new Date())) {

            throw new CouncilException("Deposit date must not be a future date");
        }
    }

    private void validateAmountTendered(PaymentDetails paymentDetails, List<ReceiptItem> receiptItems) {
        BigDecimal amount = BigDecimal.ZERO;
        for (ReceiptItem receiptItem : receiptItems) {
            amount = amount.add(receiptItem.getAmount());

        }

        if (paymentDetails.getReceiptHeader().getTotalAmount().compareTo(amount) > 0) {
            throw new CouncilException("Total amount paid can not be more than total receipt item amount");
        }

    }
}
