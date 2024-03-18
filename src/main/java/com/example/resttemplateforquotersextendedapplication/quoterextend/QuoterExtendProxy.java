package com.example.resttemplateforquotersextendedapplication.quoterextend;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class QuoterExtendProxy {

    private final RestTemplate restTemplate;

    public QuoterExtendProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${quote-extend}")
    String url;

    public String showAllQuotes() {
        // GET http://localhost:8080/api
        String uri = url + "/api";
        return getResponse(uri);
    }

    public String getById(String id) {
        // GET http://localhost:8080/api/{id}
        String uri = url + "/api/" + id;
        return getResponse(uri);
    }

    public String getRandomQuote() {
        // GET http://localhost:8080/api/random
        String uri = url + "/api/random";
        return getResponse(uri);
    }

    public String getByParam(Integer param) {
        // GET http://localhost:8080/api?id=param
        String uri = url + "/apiWithRequestParam?id=" + param;
        return getResponse(uri);
    }

    public String getByHeader() {
        // GET http://localhost:8080/apiWithHeader
        String uri = url + "/apiWithHeader";
        return getResponse(uri);
    }

    public void addQuote(String quote) {
        //http://localhost:8080/api/quote
        String uri = url + "/api/quote";
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(new QuoterQuoteValue(null, quote)), String.class);
    }

    public void deleteById(Integer id) {
        //http://localhost:8080/api/quote/{id}
        String uri = url + "/api/quote/" + id;
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);
    }

    private String getResponse(String uri) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

}