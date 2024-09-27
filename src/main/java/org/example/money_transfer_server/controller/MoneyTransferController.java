package org.example.money_transfer_server.controller;

import org.example.money_transfer_server.model.Confirmation;
import org.example.money_transfer_server.model.Response;
import org.example.money_transfer_server.model.Transfer;
import org.example.money_transfer_server.service.MoneyTransferService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://serp-ya.github.io")
public class MoneyTransferController {
    public final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping("/transfer")
    public Response transfer(@RequestBody Transfer transfer) {
        return moneyTransferService.doTransfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public Response confirmOperation(@RequestBody Confirmation confirmation) {
        return moneyTransferService.confirmOperation(confirmation);
    }
}
