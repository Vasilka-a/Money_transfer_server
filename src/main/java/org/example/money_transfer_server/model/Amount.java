package org.example.money_transfer_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Amount {
    private int value;
    private String currency;
}
