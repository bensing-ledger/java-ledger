package io.bensing.ledger.usecase;

public class DepositIntoWallet {

    private LedgerGateway ledgerGateway;

    public DepositIntoWallet(LedgerGateway ledgerGateway) {
        this.ledgerGateway = ledgerGateway;
    }

    public Object Deposit(long userId, double money, String currency) {
        return null;
    }
}
