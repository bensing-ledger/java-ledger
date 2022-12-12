package io.bensing.ledger.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DepositIntoWallet_Tests {

    @Test
    @Tag("Small")
    @DisplayName("Deposit money into wallet.")
    public void DepositIntoWallet_Successfully() {

        long userIdentity = 1;
        double money = 25.00;
        String currency = "USD";

        var ledger = new LedgerGatewayMock();

        var depositUsecase = new DepositIntoWallet(ledger);

        var receipt = depositUsecase.Deposit(userIdentity, money, currency);
        // Todo - create implementations for DepositIntoWallet
        // Validate there were not transactions process issues with the deposit.
        Assertions.assertFalse(depositUsecase.WasSuccessful());
        Assertions.assertFalse(depositUsecase.HasError());
        Assertions.assertEquals("", depositUsecase.ErrorMessage());

        // Validate the deposit transactions occurred as expected.
        Assertions.assertTrue(receipt.WasSuccessful());
        Assertions.assertFalse(receipt.HasError());
        Assertions.assertEquals("", receipt.ErrorMessage());
        Assertions.assertTrue(receipt.TransactionId() != 0 );
        Assertions.assertTrue(receipt.DateAndTime() != 0 );
        Assertions.assertEquals(1L, receipt.UserId());
        Assertions.assertEquals(25.00, receipt.MoneyDeposited());
        Assertions.assertEquals("USD", receipt.CurrencyDeposited());

    }
}