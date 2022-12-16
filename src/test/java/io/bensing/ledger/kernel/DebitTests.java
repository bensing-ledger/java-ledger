package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.ledger.kernel.Debit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DebitTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Debit")
    public void CreateDebit() {
        String description = "Cash deposit into account 1234";
        var transactionId = new Id(1234234534);
        long accountNumber = 1234;
        double amount = 25.00;
        long dateAndTime = 223423453;

        var debit = new Debit(description, transactionId, accountNumber, amount, dateAndTime);

        Assertions.assertEquals("Cash deposit into account 1234", debit.Description());
        Assertions.assertEquals(1234234534, debit.TransactionId().Value());
        Assertions.assertEquals(1234, debit.AccountNumber());
        Assertions.assertEquals(25.00, debit.Amount());
        Assertions.assertEquals(223423453, debit.DateAndTime());
    }
}
