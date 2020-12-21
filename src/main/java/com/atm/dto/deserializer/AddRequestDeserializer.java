package com.atm.dto.deserializer;

import com.atm.dto.AddRequestDto;
import com.atm.error.BillConstraintException;
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
    private final Long maxBillDenomination;
    private final Long maxBillCount;
    private final Long maxNumberOfPairs;

    public AddRequestDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TypeReference<Map<Long, Long>> typeRef = new TypeReference<Map<Long, Long>>() {
        };

        Map<Long, Long> addedBills = objectMapper.readValue(jsonParser, typeRef);

        if (maxNumberOfPairs < addedBills.size() || !billsConstrainsHold(addedBills)) {
            throw new BillConstraintException(String.format("Bills constraints violated for input: %s", objectMapper.writeValueAsString(addedBills)));
        }

        return new AddRequestDto(addedBills);
    }

    private boolean billsConstrainsHold(Map<Long, Long> addedBills) {
        return addedBills.entrySet().stream().noneMatch(addedBill -> {
            Long billValue = addedBill.getKey();
            Long numberOfBills = addedBill.getValue();

            return billValue > maxBillDenomination || numberOfBills > maxBillCount;
        });
    }
}
