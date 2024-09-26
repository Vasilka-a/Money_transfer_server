package org.example.money_transfer_server.exceptions;

public class InputDataException extends RuntimeException {
    public InputDataException(String message) {
        super(message);
    }
}
