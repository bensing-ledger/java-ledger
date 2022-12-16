package io.bensing.ledger.kernel;

import io.bensing.kernel.Outcome;

public interface LedgerGateway {
    public Outcome MakeEntry(Debit debit, Credit credit);
}
