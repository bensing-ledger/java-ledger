package io.bensing.kernel.identity;

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
