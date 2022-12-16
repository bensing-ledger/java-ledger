package io.bensing.ledger.usecase.ledger;

import io.bensing.kernel.Outcome;
import io.bensing.kernel.ledger.Credit;
import io.bensing.kernel.ledger.Debit;

public interface LedgerGateway {
    public Outcome MakeEntry(Debit debit, Credit credit);
}
