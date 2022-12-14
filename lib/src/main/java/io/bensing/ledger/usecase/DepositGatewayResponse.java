package io.bensing.ledger.usecase;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.SuccessfulOutcome;
import io.bensing.kernel.UnsuccessfulOutcome;

public class DepositGatewayResponse implements Outcome {

    private long transactionId = 0L;
    private long dateAndTime = 0L;
    private final Outcome outcome;

    /*** Constructor for a successful transaction ***/
    public DepositGatewayResponse(long transactionId, long dateAndTime) {
        this.transactionId = transactionId;
        this.dateAndTime = dateAndTime;
        this.outcome = new SuccessfulOutcome();
    }

    /** Constructor for an unsuccessful transaction ***/
    public DepositGatewayResponse(long transactionId, long dateAndTime, String errorMessage) {
        this.transactionId = transactionId;
        this.dateAndTime = dateAndTime;
        this.outcome = new UnsuccessfulOutcome(errorMessage);
    }

    public long TransactionId() {
        return this.transactionId;
    }
    public long DateAndTime() {
        return this.dateAndTime;
    }

    public boolean WasSuccessful() {
        return this.outcome.WasSuccessful();
    }
    public boolean HasError() {
        return this.outcome.HasError();
    }
    public String ErrorMessage() {
        return this.outcome.ErrorMessage();
    }

}
