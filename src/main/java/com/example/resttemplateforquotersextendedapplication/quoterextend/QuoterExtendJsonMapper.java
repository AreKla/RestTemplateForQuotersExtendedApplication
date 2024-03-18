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
class QuoterExtendJsonMapper {

    private final ObjectMapper objectMapper;

    public QuoterExtendJsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    List<QuoterQuote> mapJsonToTypeReference(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("QuoterExtendMapper could not map json");
            return new ArrayList<>(Collections.emptyList());
        }
    }

    QuoterQuote mapJsonToQuoteExample(String json) {
        try {
            return objectMapper.readValue(json, QuoterQuote.class);
        } catch (JsonProcessingException e) {
            log.error("QuoterExtendMapper could not map json");
            return new QuoterQuote(null, null);
        }
    }

}