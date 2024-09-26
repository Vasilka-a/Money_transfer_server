package org.example.money_transfer_server.exceptions;

public class TransferOrConfirmationException extends RuntimeException {
    public TransferOrConfirmationException(String message) {
        super(message);
    }
}
