package io.bensing.kernel;

/**
 * SuccessfulOutcome encapsulates and manages either successful or unsuccessful state for
 * any class requiring a success/failure outcome to be tracked.
 */
public interface Outcome {

    boolean WasSuccessful();
    boolean HasError();
    String ErrorMessage();

}
