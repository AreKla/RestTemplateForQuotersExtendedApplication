package com.example.resttemplateforquotersextendedapplication;

import com.example.resttemplateforquotersextendedapplication.quoterextend.QuoterExtendService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Log4j2
public class RestTemplateForQuotersExtendedApplication {

    @Autowired
    QuoterExtendService quoterExtendService;

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateForQuotersExtendedApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToQuoterExtendEndpoint() {

        quoterExtendService.allQuotesResponse();
        quoterExtendService.byIdResponse("3");
        quoterExtendService.randomQuoteResponse();
        quoterExtendService.byParamResponse(7);
        quoterExtendService.byHeaderResponse();
        quoterExtendService.addQuote("My new Quote 13");
        quoterExtendService.deleteById(13);

    }
}