package io.bensing.ledger.usecase.make_deposit;

import io.bensing.ledger.usecase.make_deposit.DepositReceipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DepositReceipt_Tests {

    @Test
    @Tag("small")
    @DisplayName("Create a successful deposit receipt.")
    public void CreateSuccessful_DepositReceipt(){

        long transId = 2L;
        long dateTime = 1234123412;
        long userId = 383938393;
        double money = 25.00;
        String currency = "USD";

        var receipt = new DepositReceipt(transId, dateTime, userId, money, currency);

        Assertions.assertEquals(transId, 2L);
        Assertions.assertEquals(dateTime, 1234123412);
        Assertions.assertEquals(userId, 383938393);
        Assertions.assertEquals(money, 25.00);
        Assertions.assertEquals(currency, "USD");

        Assertions.assertTrue(receipt.WasSuccessful());
        Assertions.assertFalse(receipt.HasError());
        Assertions.assertEquals("", receipt.ErrorMessage());
    }

    @Test
    @Tag("small")
    @DisplayName("Create a unsuccessful deposit receipt.")
    public void CreateUnsuccessful_DepositReceipt(){

        long transId = 2L;
        long dateTime = 1234123412;
        long userId = 383938393;
        double money = 25.00;
        String currency = "USD";
        String errorMessage = "Ledger is full, keep your cash.";

        var receipt = new DepositReceipt(transId, dateTime, userId, money, currency, errorMessage);

        Assertions.assertEquals(transId, 2L);
        Assertions.assertEquals(dateTime, 1234123412);
        Assertions.assertEquals(userId, 383938393);
        Assertions.assertEquals(money, 25.00);
        Assertions.assertEquals(currency, "USD");

        Assertions.assertFalse(receipt.WasSuccessful());
        Assertions.assertTrue(receipt.HasError());
        Assertions.assertEquals("Ledger is full, keep your cash.", receipt.ErrorMessage());
    }

}
