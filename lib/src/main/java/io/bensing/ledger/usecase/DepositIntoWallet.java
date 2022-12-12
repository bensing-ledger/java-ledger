package io.bensing.ledger.usecase;

public class DepositIntoWallet {

    private LedgerGateway ledgerGateway;

    public DepositIntoWallet(LedgerGateway ledgerGateway) {
        this.ledgerGateway = ledgerGateway;
    }

    public DepositReceipt Deposit(long userId, double money, String currency) {
        return null;
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
