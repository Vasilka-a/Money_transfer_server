package org.example.money_transfer_server;

import org.example.money_transfer_server.model.Amount;
import org.example.money_transfer_server.model.Confirmation;
import org.example.money_transfer_server.model.Response;
import org.example.money_transfer_server.model.Transfer;
import org.example.money_transfer_server.repository.MoneyTransferRepository;
import org.example.money_transfer_server.service.MoneyTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTransferServerApplicationTests {

    private final MoneyTransferRepository moneyTransferRepository = Mockito.mock(MoneyTransferRepository.class);
    private final MoneyTransferService moneyTransferService = new MoneyTransferService(moneyTransferRepository);

    @Test
    void addTransferTest() {
        Transfer transfer = new Transfer(new Amount(10000, "RUR"),
                "1111222233334444", "02/26",
                "365", "9999888877773333");
        Mockito.when(moneyTransferRepository.addBankCard(transfer)).thenReturn("1");

        Response response = moneyTransferService.doTransfer(transfer);
        assertEquals(response.getOperationId(), "1");
    }

    @Test
    void confirmTransferTest() {
        Confirmation confirmation = new Confirmation("1", "0000");
        Mockito.when(moneyTransferRepository.getTransferById("1")).thenReturn(new Transfer(new Amount(10000, "RUR"),
                "1111222233334444", "02/26",
                "365", "9999888877773333"));
        Response response = moneyTransferService.confirmOperation(confirmation);

        assertEquals(response.getOperationId(), "1");
    }
}
