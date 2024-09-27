package org.example.money_transfer_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyTransferServerApplication {

    static final Logger log = LoggerFactory.getLogger(MoneyTransferServerApplication.class);

    public static void main(String[] args) {

        log.info("Before Starting application");
        SpringApplication.run(MoneyTransferServerApplication.class, args);

    }

}
