package com.example.resttemplateforquotersextendedapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@Log4j2
public class RestTemplateForQuotersExtendedApplication {

    @Autowired
    QuoterExtendProxy quoterExtendClient;

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateForQuotersExtendedApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToQuoterExtendEndpoint() {

        allQuotesResponse();
        byIdResponse();
        randomQuoteResponse();
        byParamResponse(quoterExtendClient.getByParam(7));
        byHeaderResponse();
        quoterExtendClient.addQuote("My new Quote 13");
        quoterExtendClient.deleteById(10);

    }

    private void byHeaderResponse() {
        String byHeaderResponse = quoterExtendClient.getByHeader();
        List<QuoteExample> mapByHeaderResponse = mapJsonToTypeReference(byHeaderResponse);
        log.info(mapByHeaderResponse);
    }

    private void byParamResponse(String quoterExtendClient) {
        String byParamResponse = quoterExtendClient;
        QuoteExample mapByParamResponse = mapJsonToQuoteExample(byParamResponse);
        log.info(mapByParamResponse);
    }

    private void randomQuoteResponse() {
        byParamResponse(quoterExtendClient.getRandomQuote());
    }

    private void byIdResponse() {
        byParamResponse(quoterExtendClient.getById(3));
    }

    private void allQuotesResponse() {
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        List<QuoteExample> mapAllQuotesResponse = mapJsonToTypeReference(allQuotesResponse);
        log.info(mapAllQuotesResponse);
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