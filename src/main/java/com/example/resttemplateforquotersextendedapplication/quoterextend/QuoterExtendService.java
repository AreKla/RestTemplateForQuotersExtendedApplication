package com.example.resttemplateforquotersextendedapplication.quoterextend;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

    public String allQuotesResponse() {
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        if (allQuotesResponse == null) {
            log.error("allQuotesResponse was null");
            return "";
        }
        List<QuoteExample> mapAllQuotesResponse = quoterExtendMapper.mapJsonToTypeReference(allQuotesResponse);
        log.info("All QUOTES: " + mapAllQuotesResponse);
        return mapAllQuotesResponse.toString();
    }

    public void byIdResponse(String id) {
        String byIdResponse = quoterExtendClient.getById(id);
        QuoteExample mapByIdResposne = quoterExtendMapper.mapJsonToQuoteExample(byIdResponse);
        log.info(mapByIdResposne);
    }

    public void randomQuoteResponse() {
        String randomQuoteResponse = quoterExtendClient.getRandomQuote();
        QuoteExample mapRandomQuoteResponse = quoterExtendMapper.mapJsonToQuoteExample(randomQuoteResponse);
        log.info(mapRandomQuoteResponse);
    }

    public void byParamResponse(Integer param) {
        String byParamResponse = quoterExtendClient.getByParam(param);
        QuoteExample mapByParamResponse = quoterExtendMapper.mapJsonToQuoteExample(byParamResponse);
        log.info(mapByParamResponse);
    }

    public void byHeaderResponse() {
        String byHeaderResponse = quoterExtendClient.getByHeader();
        List<QuoteExample> mapByHeaderResponse = quoterExtendMapper.mapJsonToTypeReference(byHeaderResponse);
        log.info(mapByHeaderResponse);
    }

    public void addQuote(String quote) {
        quoterExtendClient.addQuote(quote);
    }

    public void deleteById(Integer id) {
        quoterExtendClient.deleteById(id);
    }

}