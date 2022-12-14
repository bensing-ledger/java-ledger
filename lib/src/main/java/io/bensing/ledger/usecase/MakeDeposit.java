package io.bensing.ledger.usecase;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.SuccessfulOutcome;

public class MakeDeposit implements Outcome {

    private final DepositGateway depositGateway;
    private Outcome outcome;

    // TODO - Add an IdentityGateway to the constructor arguments.
    public MakeDeposit(DepositGateway depositGateway) {
        this.depositGateway = depositGateway;
    }

    public DepositReceipt Deposit(long userId, double money, String currency) {
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
