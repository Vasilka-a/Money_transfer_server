package org.example.money_transfer_server;

import org.example.money_transfer_server.model.Amount;
import org.example.money_transfer_server.model.Confirmation;
import org.example.money_transfer_server.model.Response;
import org.example.money_transfer_server.model.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestcontainersMoneyTransferTest {
    private static Transfer transfer;
    private static Response transferResponse;

    @Container
    private static final GenericContainer<?> transferApp = new GenericContainer<>("transferapp:1.0")
            .withExposedPorts(5500);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        transfer = new Transfer(new Amount(50000, "RUR"),
                "3456555555555555",
                "02/26",
                "356",
                "0000111122224444");
    }

    @Test
    void transferTest() {
        transferResponse = restTemplate.postForObject("http://localhost:" + transferApp.getMappedPort(5500) + "/transfer", transfer, Response.class);
        Assertions.assertEquals(transferResponse.getOperationId(), "1");
    }

    @Test
    void confirmOperationTest() {
        transferResponse = restTemplate.postForObject("http://localhost:" + transferApp.getMappedPort(5500) + "/transfer", transfer, Response.class);
        Confirmation confirmation = new Confirmation(transferResponse.getOperationId(), "0000");

        Response response = restTemplate.postForObject("http://localhost:" + transferApp.getMappedPort(5500) + "/confirmOperation", confirmation, Response.class);
        Assertions.assertEquals(response.getOperationId(), "2");
    }
}
