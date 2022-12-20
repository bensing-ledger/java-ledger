package io.bensing.ledger.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreditTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Credit")
    public void CreateCredit() {

        var account = new Account(1234);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertEquals(1234, credit.Account());
        Assertions.assertEquals(25.00, credit.Amount());

    }

    @Test
    @Tag("Small")
    @DisplayName("Two same credits equal each other.")
    public void TwoCreditsEqual() {

        var credit1 = new Credit(new Account(1234), new Amount(25.00));
        var credit2 = new Credit(new Account(1234), new Amount(25.00));

        Assertions.assertTrue(credit1.Equals(credit2));

    }

    @Test
    @Tag("Small")
    @DisplayName("Two different credits do not equal each other.")
    public void TwoDifferentCreditsDoNotEqual() {

        var credit1 = new Credit(new Account(3L), new Amount(25.00));
        var credit2 = new Credit(new Account(4L), new Amount(25.00));

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit account numbers should not equate");

        credit1 = new Credit(new Account(3L), new Amount(35.00));
        credit2 = new Credit(new Account(3L), new Amount(25.00));

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit amounts should not equate");

    }


}
