package com.example.resttemplateforquotersextendedapplication;

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
        List<QuoteExample> allQuotesResponse = quoterExtendClient.showAllQuotes();
        allQuotesResponse.forEach(System.out::println);

        // --->GET BY ID<---
        QuoteExample byIdResponse = quoterExtendClient.getById(3);
        System.out.println(byIdResponse);

        // --->GET RANDOM QUOTE<---
        QuoteExample randomQuoteResponse = quoterExtendClient.getRandomQuote();
        System.out.println(randomQuoteResponse);

        // --->GET BY PARAM<---
        QuoteExample byParamResponse = quoterExtendClient.getByParam(7);
        System.out.println(byParamResponse);

        // --->GET BY HEADER<---
        List<QuoteExample> byHeaderResponse = quoterExtendClient.getByHeader();
        byHeaderResponse.forEach(System.out::println);

        // --->ADD QUOTE<---
        quoterExtendClient.addQuote("My new Quote 13");

        // --->DELETE BY ID<---
        quoterExtendClient.deleteById(10);

    }
}