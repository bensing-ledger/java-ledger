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

        // Validate there were not transactions process issues with the deposit.
        Assertions.assertFalse(depositUsecase.IsSuccess());
        Assertions.assertFalse(depositUsecase.HasError());
        Assertions.assertFalse(depositUsecase.ErrorMessage());

        // Validate the deposit transactions occurred as expected.
        Assertions.assertTrue(receipt.IsSuccess());
        Assertions.assertFalse(receipt.HasError());
        Assertions.assertTrue("", receipt.ErrorMessage());
        Assertions.assertNotNull(receipt.TransactionId());
        Assertions.assertNotNull(receipt.DateAndTime());
        Assertions.assertTrue(1L, receipt.UserId());
        Assertions.assertTrue(25.00, receipt.MoneyDeposited());
        Assertions.assertTrue("USD", receipt.CurrencyDeposited());

    }
}