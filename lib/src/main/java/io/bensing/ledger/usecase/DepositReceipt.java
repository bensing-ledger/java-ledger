package io.bensing.ledger.usecase;

public class DepositReceipt {

    public DepositReceipt() {

    }

    public long TransactionId() {
        return 0;
    }

    public long DateAndTime() {
        return 0;
    }

    public long UserId() {
        return 0;
    }

    public double MoneyDeposited() {
        return 0;
    }

    public String CurrencyDeposited() {
        return "";
    }

    public boolean WasSuccessful() {
        return false;
    }
    public boolean HasError() {
        return false;
    }

    public String ErrorMessage() {
        return "Implement Error Message";
    }
}
