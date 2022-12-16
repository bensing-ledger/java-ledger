package io.bensing.ledger.usecase.cash_deposit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CashDeposit_Tests {

    @Test
    @Tag("Small")
    @DisplayName("Deposit money into wallet.")
    public void DepositIntoWallet_Successfully() {

        long userIdentity = 1;
        double money = 25.00;
        String currency = "USD";

        var ledger = new DepositGatewayMock(2L, 1670931513);
        var usecase = new CashDeposit(ledger);

        var receipt = usecase.Deposit(userIdentity, money, currency);

        // Validate there were not transactions process issues with the deposit.
        Assertions.assertTrue(usecase.WasSuccessful());
        Assertions.assertFalse(usecase.HasError());
        Assertions.assertEquals("", usecase.ErrorMessage());

        // Validate the deposit transactions occurred as expected.
        // Check outcome of deposit transaction
        Assertions.assertTrue(receipt.WasSuccessful());
        Assertions.assertFalse(receipt.HasError());
        Assertions.assertEquals("", receipt.ErrorMessage());

        // Check receipt data
        Assertions.assertEquals(2L, receipt.TransactionId());
        Assertions.assertEquals(1670931513, receipt.DateAndTime());
        Assertions.assertEquals(1L, receipt.UserId());
        Assertions.assertEquals(25.00, receipt.MoneyDeposited());
        Assertions.assertEquals("USD", receipt.CurrencyDeposited());

    }

//    TODO - Implement a Handle DepositGateway Error
//    @Test
//    @Tag("Small")
//    @DisplayName("Handle DepositGateway Error")
//    void HandleDepositGatewayError { }

//    Testing Template - Small Tests
//    @Test
//    @Tag("Small")
//    @DisplayName("Handle DepositGateway Error")
//    void HandleDepositGatewayError { }
}