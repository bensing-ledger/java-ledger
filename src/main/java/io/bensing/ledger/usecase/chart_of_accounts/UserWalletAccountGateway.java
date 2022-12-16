package io.bensing.ledger.usecase.chart_of_accounts;

import io.bensing.ledger.kernel.Account;

public interface UserWalletAccountGateway {

    public Account RetrieveAccount(long userId);
}
