package com.ebayexample.urlfileprocessor.services.impl;

import com.ebayexample.urlfileprocessor.errorHandlers.RestTemplateResponseErrorHandler;
import com.ebayexample.urlfileprocessor.services.CounterService;
import com.ebayexample.urlfileprocessor.services.NetworkProcessor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NetworkProcessorImpl implements NetworkProcessor {

    private RestTemplate restTemplate;

    public NetworkProcessorImpl() {
        restTemplate =  new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    }
    @Override
    public void makeNetworkRequest(List<String> urls) {
        // This could be multi-threaded but when doing so, the asynchronous nature caused
        // the mock server to get overwhelmed and drop responses / return false errors
        urls.parallelStream().forEach(uri -> {
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

           if (response.getStatusCode().equals(HttpStatus.OK)) {
               CounterService.addToSuccessCounter();
           }
        });
    }
}
