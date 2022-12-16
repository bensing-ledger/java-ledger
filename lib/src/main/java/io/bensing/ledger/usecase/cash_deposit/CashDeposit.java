package io.bensing.ledger.usecase.cash_deposit;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.SuccessfulOutcome;
import io.bensing.kernel.UnsuccessfulOutcome;
import io.bensing.kernel.identity.IdentityGateway;
import io.bensing.kernel.ledger.Credit;
import io.bensing.kernel.ledger.Debit;
import io.bensing.ledger.usecase.chart_of_accounts.UserWalletAccountGateway;
import io.bensing.ledger.usecase.ledger.LedgerGateway;

import java.time.Instant;

public class CashDeposit implements Outcome {

    private final long cashAccountNumber = 101;
    private final LedgerGateway ledgerGateway;
    private final UserWalletAccountGateway userWalletAccountGateway;
    private final IdentityGateway identityGateway;
    private Outcome outcome;

    // TODO - Add io.bensing.kernel to POM
    // TODO - Replace this Outcomes references with io.bensing.kernel package Outcomes
    public CashDeposit(IdentityGateway identityGateway, UserWalletAccountGateway userWalletAccountGateway, LedgerGateway ledgerGateway) {
        this.identityGateway = identityGateway;
        this.userWalletAccountGateway = userWalletAccountGateway;
        this.ledgerGateway = ledgerGateway;
    }

    public DepositReceipt Deposit(long userId, double money, String currency) {
        var transactionId = this.identityGateway.GenerateId();
        var transactionDateAndTime = Instant.now().toEpochMilli();
        var userWalletAccount = this.userWalletAccountGateway.RetrieveAccountNumber(userId);
        var description = "Cash deposit into user waller " + userWalletAccount;
        var userWalletCredit = new Credit(description, transactionId, userWalletAccount, money, transactionDateAndTime);
        var cashAccountDebit = new Debit(description, transactionId, this.cashAccountNumber, money, transactionDateAndTime);

        var ledgerResponse = this.ledgerGateway.MakeEntry(cashAccountDebit, userWalletCredit);

        return createReceipt(userId, money, currency, transactionId, transactionDateAndTime, ledgerResponse);

    }

    private DepositReceipt createReceipt(long userId, double money, String currency, long transactionId, long transactionDateAndTime, Outcome ledgerResponse) {
        DepositReceipt receipt;
        if (ledgerResponse.WasSuccessful()) {
            this.outcome = new SuccessfulOutcome();
            receipt = new DepositReceipt(
                    transactionId,
                    transactionDateAndTime,
                    userId,
                    money,
                    currency);
        } else {
            this.outcome = new UnsuccessfulOutcome(ledgerResponse.ErrorMessage());
            receipt = new DepositReceipt(
                    transactionId,
                    transactionDateAndTime,
                    userId,
                    money,
                    currency,
                    "There was an issue making the deposit. Please try again.");
        }
        return receipt;
    }

    public boolean WasSuccessful() {
        return outcome.WasSuccessful();
    }
    public boolean HasError() {
        return outcome.HasError();
    }
    public String ErrorMessage() {
        return outcome.ErrorMessage();
    }
}
