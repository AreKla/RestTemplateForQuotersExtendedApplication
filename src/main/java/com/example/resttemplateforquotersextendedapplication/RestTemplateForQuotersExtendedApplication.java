package com.example.resttemplateforquotersextendedapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class RestTemplateForQuotersExtendedApplication {

    @Autowired
    QuoterExtendProxy quoterExtendClient;

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateForQuotersExtendedApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToQuoterExtendEndpoint() {

        // --->SHOW ALL QUOTES<---
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        List<QuoteExample> mapAllQuotesResponse = mapJsonToTypeReference(allQuotesResponse);
        mapAllQuotesResponse.forEach(System.out::println);

        // --->GET BY ID<---
        String byIdResponse = quoterExtendClient.getById(3);
        QuoteExample mapByIdResponse = mapJsonToQuoteExample(byIdResponse);
        System.out.println(mapByIdResponse);

        // --->GET RANDOM QUOTE<---
        String randomQuoteResponse = quoterExtendClient.getRandomQuote();
        QuoteExample mapRandomQuoteResponse = mapJsonToQuoteExample(randomQuoteResponse);
        System.out.println(randomQuoteResponse);

        // --->GET BY PARAM<---
        String byParamResponse = quoterExtendClient.getByParam(7);
        QuoteExample mapByParamResponse = mapJsonToQuoteExample(byParamResponse);
        System.out.println(mapByParamResponse);

        // --->GET BY HEADER<---
        String byHeaderResponse = quoterExtendClient.getByHeader();
        List<QuoteExample> mapByHeaderResponse = mapJsonToTypeReference(byHeaderResponse);
        mapByHeaderResponse.forEach(System.out::println);

        // --->ADD QUOTE<---
        quoterExtendClient.addQuote("My new Quote 13");

        // --->DELETE BY ID<---
        quoterExtendClient.deleteById(10);

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