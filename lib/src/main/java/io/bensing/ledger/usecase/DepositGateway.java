package io.bensing.ledger.usecase;

public interface DepositGateway {
    DepositGatewayResponse Deposit(long userId, double money, String currency);
}
