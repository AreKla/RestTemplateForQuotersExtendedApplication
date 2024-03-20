package com.example.resttemplateforquotersextendedapplication.fromfilequotereader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class FromFileQuoteReaderService implements QuoteFetchable {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Override
    public List<Quote> fetchAllQuotes() {
        Resource resource = resourceLoader.getResource("classpath:quotes.json");
        try {
            File file = resource.getFile();
            FileQuoteWrapper fileQuoteWrapper = objectMapper.readValue(file, FileQuoteWrapper.class);
            return fileQuoteWrapper.values()
                    .stream()
                    .map(fileQuote -> new Quote(fileQuote.quote())).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            log.error("could not map json" + e.getMessage());
        } catch (IOException e) {
            log.error("quotes.json error" + e.getMessage());
        }
        return Collections.emptyList();
    }

}