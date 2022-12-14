package io.bensing.ledger.usecase;

import java.time.Instant;

public class DepositGatewayMock implements DepositGateway {

    private long transactionId;
    private long dateAndTime;

    /***
     * This constructor creates a mock Ledger Gateway and sets the Transaction ID to be returned to the input,
     * and sets the Date and Time to be returned as the current date and time.
     *
     * @param transactionId The transaction you want returned as part of the DepositGatewayResponse when .Deposit(...) is invoked.
     */
    public DepositGatewayMock(long transactionId) {
        this.setTransactionId(transactionId);
        this.setDateAndTimeToNow();
    }

    /***
     * This constructor creates a mock Ledger Gateway and sets the Transaction ID to be returned to the input,
     * and sets the Date and Time to be returned as the current date and time.
     *
     * @param transactionId The transaction you want returned as part of the DepositGatewayResponse when .Deposit(...) is invoked.
     * @param dateAndTime The date and time you want returned as part of the DepositGatewayResponse when .Deposit(...) is invoked.
     */
    public DepositGatewayMock(long transactionId, long dateAndTime) {
        this.setTransactionId(transactionId);
        this.setDateAndTime(dateAndTime);
    }

    @Override
    public DepositGatewayResponse Deposit(long userId, double money, String currency) {
        return new DepositGatewayResponse(this.transactionId, this.dateAndTime);
    }

     private void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    private void setDateAndTimeToNow() {
        this.dateAndTime = Instant.now().toEpochMilli();
    }

    private void setDateAndTime(long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
