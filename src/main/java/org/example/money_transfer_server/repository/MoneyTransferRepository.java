package org.example.money_transfer_server.repository;

import org.example.money_transfer_server.model.Transfer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MoneyTransferRepository {

    private ConcurrentHashMap<String, Transfer> bankCardMap;
    private AtomicInteger id;

    public MoneyTransferRepository() {
        this.bankCardMap = new ConcurrentHashMap<>();
        this.id = new AtomicInteger(0);
    }

    public String addBankCard(Transfer transfer) {
        String transferId = String.valueOf(id.incrementAndGet());
        bankCardMap.put(transferId, transfer);
        return transferId;
    }

    public Transfer getTransferById(String id) {
        if (!bankCardMap.containsKey(id)) {
            return null;
        }
        return bankCardMap.get(id);
    }
}
