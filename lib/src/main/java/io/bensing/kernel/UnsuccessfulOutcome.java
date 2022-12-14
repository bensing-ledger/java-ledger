package io.bensing.kernel;

public class UnsuccessfulOutcome implements Outcome {

    private String errorMessage = "";

    public UnsuccessfulOutcome(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean WasSuccessful() {
        return false;
    }

    @Override
    public boolean HasError() {
        return true;
    }

    @Override
    public String ErrorMessage() {
        return this.errorMessage;
    }
}
