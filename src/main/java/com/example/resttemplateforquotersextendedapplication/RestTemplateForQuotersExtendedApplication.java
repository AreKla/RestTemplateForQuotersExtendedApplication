package com.example.resttemplateforquotersextendedapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

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

        // --->SHOW ALL QUOTES<---
//        String byIdResponse = quoterExtendClient.getById(7);
//        System.out.println(byIdResponse);
    }
}