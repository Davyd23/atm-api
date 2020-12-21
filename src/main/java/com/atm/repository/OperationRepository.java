package com.atm.repository;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Repository
public class OperationRepository {
    private Map<Long, Long> bills = new TreeMap<>(Collections.reverseOrder());

    public void addBills(Map<Long, Long> addedBills) {
        addedBills.forEach((key, value) -> {
            Long newBillCount = bills.getOrDefault(key, 0L) + value;
            bills.put(key, newBillCount);
        });
    }

    public void withdraw(Map<Long, Long> addedBills) {
        addedBills.forEach((key, value) -> {
            Long newBillCount = bills.get(key) - value;
            bills.put(key, newBillCount);
        });
    }

    public Long getAtmAmount(){
        return bills.entrySet().stream().map(billEntry -> billEntry.getKey() * billEntry.getValue()).reduce(0L, Long::sum);
    }
}
