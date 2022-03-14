package com.techelevator.tenmo.Exception;

public class InvalidUserChoiceException extends Exception {

    public InvalidUserChoiceException() {
        super("Cannot send money to yourself. Please choose a different user.");
    }
}