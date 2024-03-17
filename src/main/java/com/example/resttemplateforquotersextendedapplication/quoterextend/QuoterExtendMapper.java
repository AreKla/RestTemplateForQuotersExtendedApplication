package com.example.resttemplateforquotersextendedapplication.quoterextend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Log4j2
class QuoterExtendMapper {

    private final ObjectMapper objectMapper;

    public QuoterExtendMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    List<QuoteExample> mapJsonToTypeReference(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("QuoterExtendMapper could not map json");
            return new ArrayList<>(Collections.emptyList());
        }
    }

    QuoteExample mapJsonToQuoteExample(String json) {
        try {
            return objectMapper.readValue(json, QuoteExample.class);
        } catch (JsonProcessingException e) {
            log.error("QuoterExtendMapper could not map json");
            return new QuoteExample(null, null);
        }
    }

}