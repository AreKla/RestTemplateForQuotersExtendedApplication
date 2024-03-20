package com.example.resttemplateforquotersextendedapplication;

import com.example.resttemplateforquotersextendedapplication.fromfilequotereader.Quote;
import com.example.resttemplateforquotersextendedapplication.fromfilequotereader.QuoteFetchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteViewerService {

    private final List<QuoteFetchable> quoteFetchable;

    public QuoteViewerService(List<QuoteFetchable> quoteFetchable) {
        this.quoteFetchable = quoteFetchable;
    }

    public List<Quote> fetchAllQuotes() {
        List<Quote> quotesToView = new ArrayList<>();
        quoteFetchable.forEach(quoteService -> quotesToView.addAll(quoteService.fetchAllQuotes()));
        return quotesToView;
    }

}