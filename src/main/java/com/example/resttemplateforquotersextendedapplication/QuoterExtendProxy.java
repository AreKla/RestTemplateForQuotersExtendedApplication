package com.example.resttemplateforquotersextendedapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class QuoterExtendProxy {

    @Autowired
    RestTemplate restTemplate;

    @Value("${quote-extend}")
    String url;

    public List<QuoteExample> showAllQuotes() {
        // GET http://localhost:8080/api
        String uri = url + "/api";
        return getQuotes(uri);
    }

    public QuoteExample getById(Integer id) {
        // GET http://localhost:8080/api/{id}
        String uri = url + "/api/" + id;
        return getQuote(uri);
    }

    public QuoteExample getRandomQuote() {
        // GET http://localhost:8080/api/random
        String uri = url + "/api/random";
        return getQuote(uri);

    }

    public QuoteExample getByParam(Integer param) {
        // GET http://localhost:8080/api?id=param
        String uri = url + "/apiWithRequestParam?id=" + param;
        return getQuote(uri);
    }

    public List<QuoteExample> getByHeader() {
        // GET http://localhost:8080/apiWithHeader
        String uri = url + "/apiWithHeader";
        return getQuotes(uri);
    }

    public void addQuote(String quote) {
        //http://localhost:8080/api/quote
        String uri = url + "/api/quote";
        ResponseEntity<String> exchange = restTemplate.exchange(uri,
                HttpMethod.POST,
                new HttpEntity<>(new QuoteValue(null, quote)),
                String.class);

    }

    public void deleteById(Integer id) {
        //http://localhost:8080/api/quote/{id}
        String uri = url + "/api/quote/" + id;
        ResponseEntity<String> exchange = restTemplate.exchange(uri,
                HttpMethod.DELETE,
                null,
                String.class);
    }

    private List<QuoteExample> getQuotes(String uri) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    String.class);
            String json = response.getBody();
            return mapJsonToTypeReference(json);
        } catch (RestClientResponseException exception) {
            System.out.println(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private QuoteExample getQuote(String uri) {
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    String.class);
            String json = exchange.getBody();
            return mapJsonToQuoteExample(json);
        } catch (RestClientResponseException exception) {
            System.out.println(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private static List<QuoteExample> mapJsonToTypeReference(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static QuoteExample mapJsonToQuoteExample(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, QuoteExample.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}