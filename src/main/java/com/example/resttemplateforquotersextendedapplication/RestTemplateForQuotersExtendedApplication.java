package com.example.resttemplateforquotersextendedapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;

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
//        String showAllQuotesResponse = quoterExtendClient.showAllQuotes();
//        System.out.println(showAllQuotesResponse);

        // --->GET BY ID<---
//        String byIdResponse = quoterExtendClient.getById(7);
//        System.out.println(byIdResponse);

        // --->GET RANDOM QUOTE<---
//        String getRandomQuote = quoterExtendClient.getRandomQuote();
//        System.out.println(getRandomQuote);

        // --->GET BY PARAM<--- WTF?
        String byParamResponse = quoterExtendClient.getByParam(5);
        System.out.println(byParamResponse);

        // --->GET BY HEADER<---

        // --->ADD QUOTE<---

        // --->DELETE BY ID <---
    }
}