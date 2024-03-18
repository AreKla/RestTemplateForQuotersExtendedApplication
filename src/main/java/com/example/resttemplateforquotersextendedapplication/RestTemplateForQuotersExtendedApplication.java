package com.example.resttemplateforquotersextendedapplication;

import com.example.resttemplateforquotersextendedapplication.quoterextend.QuoteViewerService;
import com.example.resttemplateforquotersextendedapplication.quoterextend.QuoterExtendService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Log4j2
public class RestTemplateForQuotersExtendedApplication {

    private final QuoterExtendService quoterExtendService;

    public RestTemplateForQuotersExtendedApplication(QuoterExtendService quoterExtendService) {
        this.quoterExtendService = quoterExtendService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateForQuotersExtendedApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToQuoterExtendEndpoint() {

        log.info(quoterExtendService.allQuotesResponse());
        log.info(quoterExtendService.getQuoteById("14"));
        log.info(quoterExtendService.getRandomQuoteResponse());
        log.info(quoterExtendService.getQuoteByParam(7));
        log.info(quoterExtendService.getQuotesByHeaderResponse());
        quoterExtendService.addQuote("My new Quote 15");
        quoterExtendService.deleteById(14);
    }

}