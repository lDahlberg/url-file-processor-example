package com.ebayexample.urlfileprocessor.errorHandlers;

import com.ebayexample.urlfileprocessor.services.CounterService;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.*;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() != SUCCESSFUL;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) {
            CounterService.addToFailureCounter();
    }
}
