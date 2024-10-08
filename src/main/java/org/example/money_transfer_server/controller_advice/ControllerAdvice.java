package org.example.money_transfer_server.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.example.money_transfer_server.exceptions.InputDataException;
import org.example.money_transfer_server.exceptions.TransferOrConfirmationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<String> handlerInputData(InputDataException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferOrConfirmationException.class)
    public ResponseEntity<String> handlerTransferOrConfirmation(TransferOrConfirmationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
