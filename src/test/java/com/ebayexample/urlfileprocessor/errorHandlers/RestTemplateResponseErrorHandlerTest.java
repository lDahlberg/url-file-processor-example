package com.ebayexample.urlfileprocessor.errorHandlers;

import com.ebayexample.urlfileprocessor.services.CounterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestTemplateResponseErrorHandlerTest {

    @InjectMocks
    private RestTemplateResponseErrorHandler subject;

    @Mock
    private ClientHttpResponse httpResponse;

    @Test
    void hasErrorReturnsTrueForNonSuccess() throws IOException {
        when(httpResponse.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        boolean result = subject.hasError(httpResponse);

        assertTrue(result, "Result is true");

    }

    @Test
    void handleErrorCallsCounterService() {
        subject.handleError(httpResponse);

        int expectedFailures = 1;
        assertEquals(expectedFailures, CounterService.getFailureCounter(), "Counter has gone up by one");
    }
}