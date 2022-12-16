package io.bensing.kernel.ledger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreditTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Credit")
    public void CreateDebit() {
        String description = "Transfer into account 1234";
        long transactionId = 1234234534;
        long accountNumber = 1234;
        double amount = 25.00;
        long dateAndTime = 223423453;

        var debit = new Debit(description, transactionId, accountNumber, amount, dateAndTime);

        Assertions.assertEquals("Transfer into account 1234", debit.Description());
        Assertions.assertEquals(1234234534, debit.TransactionId());
        Assertions.assertEquals(1234, debit.AccountNumber());
        Assertions.assertEquals(25.00, debit.Amount());
        Assertions.assertEquals(223423453, debit.DateAndTime());
    }
}
