package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreditTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Credit")
    public void CreateCredit() {
        String description = "Transfer into account 1234";
        var transactionId = new Id(1234234534);
        var account = new Account(1234);
        double amount = 25.00;
        long dateAndTime = 223423453;

        var credit = new Credit(description, transactionId, account, amount, dateAndTime);

        Assertions.assertEquals("Transfer into account 1234", credit.Description());
        Assertions.assertEquals(1234234534, credit.TransactionId().Value());
        Assertions.assertEquals(1234, credit.Account().Value());
        Assertions.assertEquals(25.00, credit.Amount());
        Assertions.assertEquals(223423453, credit.DateAndTime());
    }

    @Test
    @Tag("Small")
    @DisplayName("Two same credits equal each other.")
    public void TwoCreditsEqual() {

        var credit1 = new Credit("Transfer into account 1234",
                new Id(1234234534), new Account(1234), 25.00, 1L);
        var credit2 = new Credit("Transfer into account 1234",
                new Id(1234234534), new Account(1234), 25.00, 1L);

        Assertions.assertTrue(credit1.Equals(credit2));

    }

    @Test
    @Tag("Small")
    @DisplayName("Two different credits do not equal each other.")
    public void TwoDifferentCreditsDoNotEqual() {

        var credit1 = new Credit("Message 1",
                new Id(1L), new Account(1234), 25.00, 1L);
        var credit2 = new Credit("Message 2",
                new Id(1L), new Account(1234), 25.00, 1L);

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credits descriptions should not equate");

        credit1 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(1234), 25.00, 1L);
        credit2 = new Credit("Transfer into account 1234",
                new Id(2L), new Account(1234), 25.00, 1L);

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credits transaction ids should not equate");

        credit1 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(3L), 25.00, 1L);
        credit2 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(4L), 25.00, 1L);

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit account numbers should not equate");

        credit1 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(3L), 30.00, 1L);
        credit2 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(3L), 25.00, 1L);

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit amounts should not equate");

        credit1 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(3L), 25.00, 5L);
        credit2 = new Credit("Transfer into account 1234",
                new Id(1L), new Account(3L), 25.00, 6L);

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit date & times should not equate");

    }

    @Test
    @Tag("Small")
    @DisplayName("Create an invalid Credit with an bad Account")
    public void CreateInvalidCredit_FromInvalidAccount() {

        String description = "Transfer into account 1234";
        var transactionId = new Id(1234234534);
        var account = new Account(0L);
        double amount = 25.00;
        long dateAndTime = 223423453;

        var credit = new Credit(description, transactionId, account, amount, dateAndTime);

        Assertions.assertFalse(credit.IsValid(), "Expected the credit to not be valid.");
        Assertions.assertTrue(credit.ValidationMessages().contains("The account number is invalid."),
                "The expected an invalid account number message was not provided. \n" + credit.ValidationMessages().toString());
    }
}
