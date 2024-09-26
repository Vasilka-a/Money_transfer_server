package org.example.money_transfer_server;

import org.springframework.boot.SpringApplication;

public class TestMoneyTransferServerApplication {

    public static void main(String[] args) {
        SpringApplication.from(MoneyTransferServerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
