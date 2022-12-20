package io.bensing.ledger.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AmountTests {

    @Test
    @Tag("Small")
    @DisplayName("Create an Amount")
    public void CreateAmount() {

        var amount = new Amount(25.00);

        Assertions.assertEquals(25.00, amount.Value());
        Assertions.assertTrue(amount.IsValid(), "Excepted the IsValid to be true.");
        Assertions.assertFalse(amount.IsInvalid(), "Excepted the IsInvalid to be false.");
    }

    @Test
    @Tag("Small")
    @DisplayName("Create bad Amount with a negative number.")
    public void CreateBadAmountWithNegativeNumber() {

        var amount = new Amount(-25.00);

        Assertions.assertEquals(-25.00, amount.Value());
        Assertions.assertFalse(amount.IsValid(), "Excepted the IsValid to be false.");
        Assertions.assertTrue(amount.IsInvalid(), "Excepted the IsInvalid to be true.");
        var expectedMessage = "The amount must be greater than 0.";
        Assertions.assertTrue(amount.ValidationMessages().contains(expectedMessage),
                "The expected validation message was not found.");
    }

    @Test
    @Tag("Small")
    @DisplayName("Create bad Amount with 0 for amount.")
    public void CreateBadAmountWithZero() {

        var amount = new Amount(0);

        Assertions.assertEquals(0, amount.Value());
        Assertions.assertFalse(amount.IsValid(), "Excepted the IsValid to be false.");
        Assertions.assertTrue(amount.IsInvalid(), "Excepted the IsInvalid to be true.");
        var expectedMessage = "The amount must be greater than 0.";
        Assertions.assertTrue(amount.ValidationMessages().contains(expectedMessage),
                "The expected validation message was not found.");
    }

}
