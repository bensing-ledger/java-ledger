package io.bensing.ledger.usecase.make_deposit;

public interface DepositGateway {
    DepositGatewayResponse Deposit(long userId, double money, String currency);
}
