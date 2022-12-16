package io.bensing.kernel.identity;

public class IdentityGatewayMock implements IdentityGateway{
    private final long expectedId;

    public IdentityGatewayMock(long expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public long GenerateId() {
        return this.expectedId;
    }
}
