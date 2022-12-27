package io.bensing.ledger.kernel;

import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AmountTests {

    @SmallTest
    @DisplayName("Create an Amount")
    public void CreateAmount() {

        var amount = new Amount(25.00);

        Assertions.assertEquals(25.00, amount.Value());
        Assertions.assertTrue(amount.IsValid(), "Excepted the IsValid to be true.");
        Assertions.assertFalse(amount.IsInvalid(), "Excepted the IsInvalid to be false.");
    }

    @SmallTest
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

    @SmallTest
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

    @SmallTest
    @DisplayName("Two of the same amounts should equal")
    public void TwoSameAmountsEqual() {

        var amount1 = new Amount(100);
        var amount2 = new Amount(100);

        Assertions.assertTrue(amount1.Equals(amount2), "Expected both amounts to equal each other, but they did not.");
    }

    @SmallTest
    @DisplayName("Two different amounts should NOT equal")
    public void TwoDifferentAmountsDoNotEqual() {

        var amount1 = new Amount(100);
        var amount2 = new Amount(99);

        Assertions.assertFalse(amount1.Equals(amount2), "Expected both amounts to NOT equal each other, but they are equal.");
    }

}
