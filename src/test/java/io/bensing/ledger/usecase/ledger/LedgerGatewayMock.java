package io.bensing.ledger.usecase.ledger;

import io.bensing.kernel.Outcome;

public class LedgerGatewayMock implements LedgerGateway{

    private final Outcome expectedOutcome;

    public LedgerGatewayMock(Outcome expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }
    @Override
    public Outcome MakeEntry(Debit debit, Credit credit) {
        return this.expectedOutcome;
    }

}
