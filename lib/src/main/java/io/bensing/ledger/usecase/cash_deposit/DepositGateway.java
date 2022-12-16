package io.bensing.ledger.usecase.cash_deposit;

public interface DepositGateway {
    DepositGatewayResponse Deposit(long userId, double money, String currency);
}
