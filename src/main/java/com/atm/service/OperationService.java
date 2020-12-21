package com.atm.service;

import com.atm.error.AmountContraintException;
import com.atm.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final Long maxTotalAmount;

    @Autowired
    public OperationService(OperationRepository operationRepository, @Value("${max.atm.amount}") Long maxTotalAmount) {
        this.operationRepository = operationRepository;
        this.maxTotalAmount = maxTotalAmount;
    }

    public void addMoney(Map<Long, Long> bills) {
        Long addedAmount = calculateBillsAmount(bills);
        if (operationRepository.getAtmAmount() + addedAmount > maxTotalAmount){
            throw new AmountContraintException("ATM money overflow");
        }

        operationRepository.addMoney(addedAmount);
    }

    private Long calculateBillsAmount(Map<Long, Long> billsMap){
        return billsMap.entrySet().stream().map(billEntry -> billEntry.getKey() * billEntry.getValue()).reduce(0L, (a, b) -> a + b);
    }
}
