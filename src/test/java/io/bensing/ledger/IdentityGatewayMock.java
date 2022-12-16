package io.bensing.ledger;

import io.bensing.kernel.identity.Id;
import io.bensing.kernel.identity.IdentityGateway;

public class IdentityGatewayMock implements IdentityGateway {


    private final Id expectedId;

    public IdentityGatewayMock(Id expectedId) {
        this.expectedId = expectedId;
    }
    @Override
    public Id GenerateId() {
        return this.expectedId;
    }
}
