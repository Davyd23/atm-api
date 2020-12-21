package com.atm.service;

import com.atm.error.AmountContraintException;
import com.atm.error.CouldNotSplitValueException;
import com.atm.error.InsufficientFundsException;
import com.atm.repository.OperationRepository;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

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
        if (operationRepository.getAtmAmount() + addedAmount > maxTotalAmount) {
            throw new AmountContraintException("ATM money overflow");
        }

        operationRepository.addBills(bills);
    }

    public Map<Long, Long> withdraw(Long amount) {
        if (amount > operationRepository.getAtmAmount()) {
            throw new InsufficientFundsException("Cannot handle the requested amount");
        }

        Map<Long, Long> returnedBills = new TreeMap<>();
        Map<Long, Long> bills = operationRepository.getBills();

        AtomicLong remainingValueToWithdraw = new AtomicLong(amount);
        bills.forEach((denomination, count) -> {
            if (denomination < remainingValueToWithdraw.get()) {
                long numberOfBills = remainingValueToWithdraw.get() / denomination;
                numberOfBills = numberOfBills < count ? numberOfBills : count;
                returnedBills.put(denomination, numberOfBills);

                remainingValueToWithdraw.set(remainingValueToWithdraw.get() - (denomination * numberOfBills));
            }
        });

        if(remainingValueToWithdraw.get() != 0){
            throw new CouldNotSplitValueException(String.format("Could not split: %s", amount));
        }

        operationRepository.withdraw(returnedBills);
        return returnedBills;
    }

    private Long calculateBillsAmount(Map<Long, Long> billsMap) {
        return billsMap.entrySet().stream().map(billEntry -> billEntry.getKey() * billEntry.getValue()).reduce(0L, Long::sum);
    }
}
