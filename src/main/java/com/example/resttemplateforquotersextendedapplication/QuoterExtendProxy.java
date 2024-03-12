package com.example.resttemplateforquotersextendedapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        String json = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public QuoteExample getById(Integer id) {
        // GET http://localhost:8080/api/id
        String uri = url + "/api/" + id;
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        String json = exchange.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, QuoteExample.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public QuoteExample getRandomQuote() {
        // GET http://localhost:8080/api/random
        String uri = url + "/api/random";
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        String json = exchange.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, QuoteExample.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public QuoteExample getByParam(Integer param) {
        // GET http://localhost:8080/api?id=param
        String uri = url + "/apiWithRequestParam?id=" + param;
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        String json = exchange.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, QuoteExample.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<QuoteExample> getByHeader() {
        // GET http://localhost:8080/apiWithHeader
        String uri = url + "/apiWithHeader";
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        String json = exchange.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}