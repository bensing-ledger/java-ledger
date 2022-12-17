package io.bensing.ledger.usecase.deposit;

import io.bensing.kernel.identity.Id;
import io.bensing.ledger.kernel.*;
import io.bensing.ledger.usecase.chart_of_accounts.UserWalletAccountGateway;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.SuccessfulOutcome;
import io.bensing.kernel.UnsuccessfulOutcome;
import io.bensing.kernel.identity.IdentityGateway;

import java.time.Instant;

public class CashDeposit implements Outcome {

    private final Account cashAccount = new Account(101);
    private final LedgerGateway ledgerGateway;
    private final UserWalletAccountGateway userWalletAccountGateway;
    private final IdentityGateway identityGateway;
    private Outcome outcome;

    public CashDeposit(IdentityGateway identityGateway, UserWalletAccountGateway userWalletAccountGateway, LedgerGateway ledgerGateway) {
        this.identityGateway = identityGateway;
        this.userWalletAccountGateway = userWalletAccountGateway;
        this.ledgerGateway = ledgerGateway;
    }

    // TODO - Create a CashDeposit slip as the argument for Deposit; add validations to it.
    public DepositReceipt Deposit(long userId, double amount, String currency) {

        var transactionId = this.identityGateway.GenerateId();
        var transactionDateAndTime = Instant.now().toEpochMilli();
        var userWalletAccount = this.userWalletAccountGateway.RetrieveAccount(userId);
        var description = "Cash deposit into user waller " + userWalletAccount;

        var journalEntry = new JournalEntry(description, transactionId, transactionDateAndTime, amount, userWalletAccount, this.cashAccount);

        var ledgerResponse = this.ledgerGateway.MakeEntry(journalEntry);

        return createReceipt(userId, amount, currency, transactionId, transactionDateAndTime, ledgerResponse);

    }

    private DepositReceipt createReceipt(long userId, double money, String currency, Id transactionId, long transactionDateAndTime, Outcome ledgerResponse) {
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
