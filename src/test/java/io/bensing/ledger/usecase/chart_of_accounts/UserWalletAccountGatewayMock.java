package io.bensing.ledger.usecase.chart_of_accounts;

import io.bensing.ledger.kernel.Account;

public class UserWalletAccountGatewayMock implements UserWalletAccountGateway {

    private final Account expectedAccount;

    public UserWalletAccountGatewayMock(Account expectedAccount) {
        this.expectedAccount = expectedAccount;
    }

    public Account RetrieveAccount(long userId) {
        return this.expectedAccount;
    }
}
