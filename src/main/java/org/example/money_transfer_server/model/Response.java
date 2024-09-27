package org.example.money_transfer_server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String operationId;

    public Response() {

    }

    public Response(String operationId) {
        this.operationId = operationId;
    }
}
