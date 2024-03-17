package com.example.resttemplateforquotersextendedapplication.quoterextend;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class QuoterExtendService {

    private final QuoterExtendProxy quoterExtendClient;

    public QuoterExtendService(QuoterExtendProxy quoterExtendClient) {
        this.quoterExtendClient = quoterExtendClient;
    }

    public void allQuotesResponse() {
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        List<QuoteExample> mapAllQuotesResponse = QuoterExtendMapper.mapJsonToTypeReference(allQuotesResponse);
        log.info(mapAllQuotesResponse);
    }

    public void byIdResponse(String id) {
        String byIdResponse = quoterExtendClient.getById(id);
        QuoteExample mapByIdResposne = QuoterExtendMapper.mapJsonToQuoteExample(byIdResponse);
        log.info(mapByIdResposne);
    }

    public void randomQuoteResponse() {
        String randomQuoteResponse = quoterExtendClient.getRandomQuote();
        QuoteExample mapRandomQuoteResponse = QuoterExtendMapper.mapJsonToQuoteExample(randomQuoteResponse);
        log.info(mapRandomQuoteResponse);
    }

    public void byParamResponse(Integer param) {
        String byParamResponse = quoterExtendClient.getByParam(param);
        QuoteExample mapByParamResponse = QuoterExtendMapper.mapJsonToQuoteExample(byParamResponse);
        log.info(mapByParamResponse);
    }

    public void byHeaderResponse() {
        String byHeaderResponse = quoterExtendClient.getByHeader();
        List<QuoteExample> mapByHeaderResponse = QuoterExtendMapper.mapJsonToTypeReference(byHeaderResponse);
        log.info(mapByHeaderResponse);
    }

    public void addQuote(String quote) {
        quoterExtendClient.addQuote(quote);
    }

    public void deleteById(Integer id) {
        quoterExtendClient.deleteById(id);
    }
}