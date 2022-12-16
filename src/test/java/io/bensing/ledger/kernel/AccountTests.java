package io.bensing.ledger.kernel;

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
    }
}
