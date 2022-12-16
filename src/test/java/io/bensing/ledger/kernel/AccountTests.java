package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AccountTests {

    @Test
    @Tag("Small")
    @DisplayName("Create Account value object")
    public void CreateAccount() {

        var account = new Account(1L);

        Assertions.assertEquals(1L, account.Value());
        Assertions.assertTrue(new Id(1L).Equals(account.Number()));
    }

    @Test
    @Tag("Small")
    @DisplayName("Accounts with the same Id are equal")
    public void AccountsAreEqual() {

        var account1 = new Account(1L);
        var account2 = new Account(1L);

        Assertions.assertTrue(account1.Equals(account2));
    }

    @Test
    @Tag("Small")
    @DisplayName("Accounts with the different Id are not equal")
    public void AccountsAreNotEqual() {

        var account1 = new Account(1L);
        var account2 = new Account(2L);

        Assertions.assertFalse(account1.Equals(account2));
    }
}
