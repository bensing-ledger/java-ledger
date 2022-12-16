package io.bensing.ledger.usecase.make_deposit;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.SuccessfulOutcome;
import io.bensing.kernel.UnsuccessfulOutcome;

public class DepositReceipt implements Outcome {

    private long transactionId = 0L;
    private long datAndTime = 0L;
    private long userId = 0L;
    private double money = 00.00;
    private String currency = "";
    private final Outcome outcome;

    /**
     * Create an instance of a successful deposit receipt.
     *
     * @param transactionId The Transaction ID of the deposit transaction
     * @param dateAndTime The Date and Time of the deposit transaction
     * @param userId The User ID the transaction was for
     * @param money The amount of money the transaction was for
     * @param currency The currency of the money for the transaction
     */
    public DepositReceipt(long transactionId, long dateAndTime, long userId, double money, String currency) {
        this.transactionId = transactionId;
        this.datAndTime = dateAndTime;
        this.userId = userId;
        this.money = money;
        this.currency = currency;
        this.outcome = new SuccessfulOutcome();
    }

    /***
     * Create an instance of an unsuccessful deposit receipt.
     *
     * @param transactionId The Transaction ID of the deposit transaction
     * @param dateAndTime The Date and Time of the deposit transaction
     * @param userId The User ID the transaction was for
     * @param money The amount of money the transaction was for
     * @param currency The currency of the money for the transaction
     */
    public DepositReceipt(long transactionId, long dateAndTime, long userId, double money, String currency, String errorMessage) {
        this.transactionId = transactionId;
        this.datAndTime = dateAndTime;
        this.userId = userId;
        this.money = money;
        this.currency = currency;
        this.outcome = new UnsuccessfulOutcome(errorMessage);
    }

    public long TransactionId() {
        return this.transactionId;
    }

    public long DateAndTime() {
        return this.datAndTime;
    }

    public long UserId() {
        return this.userId;
    }

    public double MoneyDeposited() {
        return this.money;
    }

    public String CurrencyDeposited() {
        return this.currency;
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
