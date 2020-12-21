package com.atm.configuration;

import com.atm.dto.AddRequestDto;
import com.atm.dto.deserializer.AddRequestDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public Module addRequestModule(AddRequestDeserializer addRequestDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(AddRequestDto.class, addRequestDeserializer);
        return module;
    }

    @Bean
    public AddRequestDeserializer addRequestDeserializer(@Value("${max.bill.denomination}") Long maxDenomination,
                                                         @Value("${max.bill.count}") Long maxCount,
                                                         @Value("${max.bill.pairs}") Long maxPairs) {
        return new AddRequestDeserializer(maxDenomination, maxCount, maxPairs);
    }
}
