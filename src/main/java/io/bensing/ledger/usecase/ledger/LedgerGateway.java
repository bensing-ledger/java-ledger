package io.bensing.ledger.usecase.ledger;

import io.bensing.kernel.Outcome;

public interface LedgerGateway {
    public Outcome MakeEntry(Debit debit, Credit credit);
}
