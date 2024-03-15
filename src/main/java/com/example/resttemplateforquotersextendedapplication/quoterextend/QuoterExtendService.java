package com.example.resttemplateforquotersextendedapplication.quoterextend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class QuoterExtendService {

    @Autowired
    QuoterExtendProxy quoterExtendClient;

    public void allQuotesResponse() {
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        List<QuoteExample> mapAllQuotesResponse = mapJsonToTypeReference(allQuotesResponse);
        log.info(mapAllQuotesResponse);
    }

    public void byIdResponse(String id) {
        String byIdResponse = quoterExtendClient.getById(id);
        QuoteExample mapByIdResposne = mapJsonToQuoteExample(byIdResponse);
        log.info(mapByIdResposne);
    }

    public void randomQuoteResponse() {
        String randomQuoteResponse = quoterExtendClient.getRandomQuote();
        QuoteExample mapRandomQuoteResponse = mapJsonToQuoteExample(randomQuoteResponse);
        log.info(mapRandomQuoteResponse);
    }

    public void byParamResponse(Integer param) {
        String byParamResponse = quoterExtendClient.getByParam(param);
        QuoteExample mapByParamResponse = mapJsonToQuoteExample(byParamResponse);
        log.info(mapByParamResponse);
    }

    public void byHeaderResponse() {
        String byHeaderResponse = quoterExtendClient.getByHeader();
        List<QuoteExample> mapByHeaderResponse = mapJsonToTypeReference(byHeaderResponse);
        log.info(mapByHeaderResponse);
    }

    public void addQuote(String quote) {
        quoterExtendClient.addQuote(quote);
    }

    public void deleteById(Integer id) {
        quoterExtendClient.deleteById(id);
    }

    private static List<QuoteExample> mapJsonToTypeReference(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static QuoteExample mapJsonToQuoteExample(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, QuoteExample.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}