package com.example.resttemplateforquotersextendedapplication.quoterextend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class FromFileQuoteReaderService implements QuoteFetchable {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public FromFileQuoteReaderService(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Quote> fetchAllQuotes() {
        Resource resource = resourceLoader.getResource("classpath:quotes.json");
        try {
            File file = resource.getFile();
            List<FileQuoteWrapper> fileQuoteWrapper = Collections.singletonList(objectMapper.readValue(file, FileQuoteWrapper.class));
//            FileQuote fileQuote = objectMapper.readValue(file, FileQuote.class);
            return fileQuoteWrapper.stream().map(fileQuoteWrapper1 -> new Quote(fileQuoteWrapper1.value().quote())).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            log.error("clud not map json" + e.getMessage());
        } catch (IOException e) {
            log.error("quotes.json error" + e.getMessage());
        }
        return Collections.emptyList();
    }
}
