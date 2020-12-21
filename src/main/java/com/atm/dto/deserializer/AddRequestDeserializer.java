package com.atm.dto.deserializer;

import com.atm.dto.AddRequestDto;
import com.atm.error.BillConstraintViolation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class AddRequestDeserializer extends JsonDeserializer<AddRequestDto> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Integer maxBillDenomination;
    private final Integer maxBillCount;
    private final Integer maxNumberOfPairs;

    public AddRequestDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TypeReference<Map<Integer, Integer>> typeRef = new TypeReference<Map<Integer, Integer>>() {
        };

        Map<Integer, Integer> addedBills = objectMapper.readValue(jsonParser, typeRef);

        if (maxNumberOfPairs < addedBills.size() || !billsConstrainsHold(addedBills)) {
            throw new BillConstraintViolation(String.format("Bills constraints violated for input: %s", objectMapper.writeValueAsString(addedBills)));
        }

        return new AddRequestDto(addedBills);
    }

    private boolean billsConstrainsHold(Map<Integer, Integer> addedBills) {
        return addedBills.entrySet().stream().noneMatch(addedBill -> {
            Integer billValue = addedBill.getKey();
            Integer numberOfBills = addedBill.getValue();

            return billValue > maxBillDenomination || numberOfBills > maxBillCount;
        });
    }
}
