package io.bensing.ledger.usecase.cash_deposit;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.SuccessfulOutcome;

public class CashDeposit implements Outcome {

    private final DepositGateway depositGateway;
    private Outcome outcome;

    // TODO - Add io.bensing.kernel to POM
    // TODO - Replace this Outcomes references with io.bensing.kernel package Outcomes
    // TODO - Add an IdentityGateway to the constructor arguments.
    public CashDeposit(DepositGateway depositGateway) {
        this.depositGateway = depositGateway;
    }

    public DepositReceipt Deposit(long userId, double money, String currency) {
        // TODO - Create the debit and credit transactions
        // Sequence - Get the "CASH" credit account number          Create ChartOfAccountsGateway
        // Sequence - Get the "Wallet" account number for user
        // Create the credit for [account]                          create a Credit value object
        // Create the debit for [account]                           create a Debit value object
        // Update the chart of accounts with the debit & credit     Create UpdateLedgerGateway - refactor deposit gateway

        var gatewayResponse = this.depositGateway.Deposit(userId, money, currency);
        this.outcome = new SuccessfulOutcome();
        return new DepositReceipt(
                gatewayResponse.TransactionId(),
                gatewayResponse.DateAndTime(),
                userId,
                money,
                currency);
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
