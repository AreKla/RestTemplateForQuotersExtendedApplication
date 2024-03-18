package com.example.resttemplateforquotersextendedapplication.quoterextend;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class QuoterExtendService {

    private final QuoterExtendProxy quoterExtendClient;
    private final QuoterExtendMapper quoterExtendMapper;

    public QuoterExtendService(QuoterExtendProxy quoterExtendClient, QuoterExtendMapper quoterExtendMapper) {
        this.quoterExtendClient = quoterExtendClient;
        this.quoterExtendMapper = quoterExtendMapper;
    }

    public List<QuoteExample> allQuotesResponse() {
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        if (allQuotesResponse == null) {
            log.error("allQuotesResponse was null");
            return Collections.emptyList();
        }
        return quoterExtendMapper.mapJsonToTypeReference(allQuotesResponse);
    }

    public QuoteExample byIdResponse(String id) {
        String byIdResponse = quoterExtendClient.getById(id);
        return quoterExtendMapper.mapJsonToQuoteExample(byIdResponse);
    }

    public QuoteExample randomQuoteResponse() {
        String randomQuoteResponse = quoterExtendClient.getRandomQuote();
        return quoterExtendMapper.mapJsonToQuoteExample(randomQuoteResponse);
    }

    public QuoteExample byParamResponse(Integer param) {
        String byParamResponse = quoterExtendClient.getByParam(param);
        return quoterExtendMapper.mapJsonToQuoteExample(byParamResponse);
    }

    public List<QuoteExample> byHeaderResponse() {
        String byHeaderResponse = quoterExtendClient.getByHeader();
        return quoterExtendMapper.mapJsonToTypeReference(byHeaderResponse);
    }

    public void addQuote(String quote) {
        quoterExtendClient.addQuote(quote);
        log.info("You added quote:\n" + quote);
    }

    public void deleteById(Integer id) {
        quoterExtendClient.deleteById(id);
        log.info("You deleted quote by " + id + " id");
    }

}