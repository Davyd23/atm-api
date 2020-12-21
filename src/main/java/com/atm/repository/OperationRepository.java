package com.atm.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class OperationRepository {
    private Long atmAmount = 0L;

    public void addMoney(Long value) {
        this.atmAmount = this.atmAmount + value;
    }
}
