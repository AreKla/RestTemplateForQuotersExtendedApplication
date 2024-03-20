package com.example.resttemplateforquotersextendedapplication.quoterextend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoterExtendServiceTest {

    @Mock
    private QuoterExtendProxy quoterExtendProxy;

    @Mock
    private QuoterExtendJsonMapper quoterExtendJsonMapper;

    @InjectMocks
    private QuoterExtendService quoterExtendService;

    @Test
    void allQuotesResponse_Success() {
        // Given
        String mockResponseJson = "{\"values\": [{\"id\": 1, \"quote\": \"Test Quote 1\"}]}";
        List<QuoterQuote> mockQuotes = List.of(new QuoterQuote("quoteType", new QuoterQuoteValue(1L, "Test Quote 1")));

        when(quoterExtendProxy.showAllQuotes()).thenReturn(mockResponseJson);
        when(quoterExtendJsonMapper.mapJsonToTypeReference(mockResponseJson)).thenReturn(mockQuotes);

        // When
        List<QuoterQuote> quotes = quoterExtendService.allQuotesResponse();

        // Then
        assertNotNull(quotes);
        assertFalse(quotes.isEmpty());
        assertEquals(1, quotes.size());
        assertEquals("Test Quote 1", quotes.get(0).value().quote());
    }
}
