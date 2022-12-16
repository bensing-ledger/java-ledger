package io.bensing.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Outcome_Tests {

    @Test
    @Tag("small")
    @DisplayName("Create a SuccessfulOutcome which represents a successful response.")
    public void CreateSuccessfulOutcome() {
        var outcome = new SuccessfulOutcome();
        Assertions.assertTrue(outcome.WasSuccessful());
        Assertions.assertFalse(outcome.HasError());
        Assertions.assertEquals("", outcome.ErrorMessage());
    }

    @Test
    @Tag("small")
    @DisplayName("Create a UnsuccessfulOutcome which represents a unsuccessful response.")
    public void CreateUnsuccessfulOutcome() {
        var errorMessage = "The Error You Are Supposed To Get";
        var outcome = new UnsuccessfulOutcome(errorMessage);

        Assertions.assertFalse(outcome.WasSuccessful());
        Assertions.assertTrue(outcome.HasError());
        Assertions.assertEquals("The Error You Are Supposed To Get", outcome.ErrorMessage());
    }


}
