package org.example.money_transfer_server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.money_transfer_server.exceptions.InputDataException;
import org.example.money_transfer_server.exceptions.TransferOrConfirmationException;
import org.example.money_transfer_server.model.Confirmation;
import org.example.money_transfer_server.model.Response;
import org.example.money_transfer_server.model.Transfer;
import org.example.money_transfer_server.repository.MoneyTransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class MoneyTransferService {

    private MoneyTransferRepository moneyTransferRepository;

    public Response doTransfer(Transfer transfer) {
        validateTransfer(transfer);
        String transferId = moneyTransferRepository.addBankCard(transfer);
        log.info("Transfer: id - {} - {} commission: {}", transferId, transfer, transfer.getAmount().getValue() / 100);
        return new Response(transferId);
    }

    public Response confirmOperation(Confirmation confirmation) {
        Transfer transfer = moneyTransferRepository.confirmOperation(confirmation.getOperationId());
        if (confirmation.getCode().equals("0000") && transfer != null) {
            log.info("Transfer: {} successful", confirmation.getOperationId());
        } else {
            throw new TransferOrConfirmationException("Transfer id: " + confirmation.getOperationId() + " failed");
        }
        return new Response(confirmation.getOperationId());
    }

    public void validateTransfer(Transfer transfer) {
        String[] parts = transfer.getCardFromValidTill().split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        int currentYear = LocalDate.now().getYear() % 100;
        if (transfer.getCardFromNumber() == null && transfer.getCardFromNumber().length() == 16) {
            throw new InputDataException("Card number must be at least 16 characters");
        }
        if (transfer.getCardToNumber() == null && transfer.getCardToNumber().length() == 16) {
            throw new InputDataException("Card number must be at least 16 characters");
        }
        if (transfer.getAmount().getValue() < 0) {
            throw new InputDataException("Amount must be greater than zero");
        }
        if (transfer.getCardFromCVV().length() != 3) {
            throw new InputDataException("Card from CVV must be 3 characters");
        }
        if (month < 1 || month > 12 || year < currentYear) {
            throw new InputDataException("Invalid month or year");
        }
    }
}
