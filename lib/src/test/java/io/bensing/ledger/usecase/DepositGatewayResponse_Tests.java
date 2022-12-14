package io.bensing.ledger.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class DepositGatewayResponse_Tests {

    @Test
    @Tag("small")
    @DisplayName("Create a DepositGatewayResponse which represents a successful response.")
    public void CreateDepositGatewayResponse_Successful() {
        var transactionId = 2L;
        var dateAndTime = Instant.now().toEpochMilli();
        var response = new DepositGatewayResponse(transactionId, dateAndTime);

        Assertions.assertEquals(2L, response.TransactionId());
        Assertions.assertEquals(response.DateAndTime(), dateAndTime);
        Assertions.assertTrue(response.WasSuccessful());
        Assertions.assertFalse(response.HasError());
        Assertions.assertEquals("", response.ErrorMessage());
    }

    @Test
    @Tag("small")
    @DisplayName("Create a DepositGatewayResponse which represents a unsuccessful response.")
    public void CreateDepositGatewayResponse_NotSuccessful() {
        var transactionId = 2L;
        var dateAndTime = Instant.now().toEpochMilli();
        var errorMessage = "The Error You Are Supposed To Get";
        var response = new DepositGatewayResponse(transactionId, dateAndTime, errorMessage);

        Assertions.assertEquals(2L, response.TransactionId());
        Assertions.assertEquals(response.DateAndTime(), dateAndTime);
        Assertions.assertFalse(response.WasSuccessful());
        Assertions.assertTrue(response.HasError());
        Assertions.assertEquals("The Error You Are Supposed To Get", response.ErrorMessage());
    }
}
