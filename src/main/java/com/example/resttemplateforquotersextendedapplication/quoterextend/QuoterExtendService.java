package com.example.resttemplateforquotersextendedapplication.quoterextend;

import com.example.resttemplateforquotersextendedapplication.fromfilequotereader.Quote;
import com.example.resttemplateforquotersextendedapplication.fromfilequotereader.QuoteFetchable;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class QuoterExtendService implements QuoteFetchable {

    private final QuoterExtendProxy quoterExtendClient;
    private final QuoterExtendJsonMapper quoterExtendJsonMapper;

    public List<QuoterQuote> allQuotesResponse() {
        String allQuotesResponse = quoterExtendClient.showAllQuotes();
        if (allQuotesResponse == null) {
            log.error("Failed to retrieve all quotes. The response was null." +
                    "Please check the connection or the availability of quotes.");
            return Collections.emptyList();
        }
        return quoterExtendJsonMapper.mapJsonToTypeReference(allQuotesResponse);
    }

    public QuoterQuote getQuoteById(String id) {
        String getQuoteByIdResponse = quoterExtendClient.getById(id);
        return quoterExtendJsonMapper.mapJsonToQuoteExample(getQuoteByIdResponse);
    }

    public QuoterQuote getRandomQuoteResponse() {
        String getRandomQuoteResponse = quoterExtendClient.getRandomQuote();
        return quoterExtendJsonMapper.mapJsonToQuoteExample(getRandomQuoteResponse);
    }

    public QuoterQuote getQuoteByParam(Integer param) {
        String getQuoteByParamResponse = quoterExtendClient.getByParam(param);
        return quoterExtendJsonMapper.mapJsonToQuoteExample(getQuoteByParamResponse);
    }

    public List<QuoterQuote> getQuotesByHeaderResponse() {
        String getQuotesByHeaderResponse = quoterExtendClient.getByHeader();
        return quoterExtendJsonMapper.mapJsonToTypeReference(getQuotesByHeaderResponse);
    }

    public void addQuote(String quote) {
        quoterExtendClient.addQuote(quote);
        log.info("Successfully added the following quote: " + quote);
    }

    public void deleteById(Integer id) {
        quoterExtendClient.deleteById(id);
        log.info("Successfully deleted the quote with ID: " + id);
    }

    @Override
    public List<Quote> fetchAllQuotes() {
        List<QuoterQuote> quoterQuoteList = allQuotesResponse();
        List<Quote> quoterQuoteCollect = quoterQuoteList.stream().map(quoterQuote -> new Quote(quoterQuote.value().quote())).toList();
        return quoterQuoteCollect;
    }
}