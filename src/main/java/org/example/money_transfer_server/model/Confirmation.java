package org.example.money_transfer_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Confirmation {
    private String operationId;
    private String code;
}
