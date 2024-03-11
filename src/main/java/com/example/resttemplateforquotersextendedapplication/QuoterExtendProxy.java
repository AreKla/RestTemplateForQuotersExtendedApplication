package com.example.resttemplateforquotersextendedapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoterExtendProxy {

    @Autowired
    RestTemplate restTemplate;

    public String showAllQuotes() {
        // GET http://localhost:8080/api
        String uri = "http://localhost:8080/api";
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        return exchange.getBody();
    }

    public String getById(Integer id) {
        // GET http://localhost:8080/api/id
        String uri = "http://localhost:8080/api/" + id;
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        return exchange.getBody();
    }

    public String getRandomQuote() {
        // GET http://localhost:8080/api/random
        String uri = "http://localhost:8080/api/random";
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        return exchange.getBody();
    }

    public String getByParam(Integer param) {
        // GET http://localhost:8080/api?id=param
        String uri = "http://localhost:8080/api?id=" + param;
        ResponseEntity<String> exchange = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                String.class
        );
        return exchange.getBody();
    }
}