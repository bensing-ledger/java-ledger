package io.bensing.ledger.usecase.ledger;

import io.bensing.kernel.Outcome;
import io.bensing.ledger.kernel.Credit;
import io.bensing.ledger.kernel.Debit;
import io.bensing.ledger.kernel.LedgerGateway;

public class LedgerGatewayMock implements LedgerGateway {

    private final Outcome expectedOutcome;

    public LedgerGatewayMock(Outcome expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }
    @Override
    public Outcome MakeEntry(Debit debit, Credit credit) {
        return this.expectedOutcome;
    }

}
