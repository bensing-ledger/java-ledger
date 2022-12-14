package io.bensing.kernel;

public class SuccessfulOutcome implements Outcome {

    public SuccessfulOutcome() {
    }

    @Override
    public boolean WasSuccessful() {
        return true;
    }

    @Override
    public boolean HasError() {
        return false;
    }

    @Override
    public String ErrorMessage() {
        return "";
    }
}
