package io.bensing.ledger.usecase.chart_of_accounts;

public class UserWalletAccountGatewayMock implements UserWalletAccountGateway {

    private final long expectedAccountNumber;

    public UserWalletAccountGatewayMock(long expectedAccountNumber) {
        this.expectedAccountNumber = expectedAccountNumber;
    }
    @Override
    public long RetrieveAccountNumber(long userId) {
        return this.expectedAccountNumber;
    }
}
