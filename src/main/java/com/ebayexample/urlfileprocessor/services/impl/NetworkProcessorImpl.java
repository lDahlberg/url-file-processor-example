package com.ebayexample.urlfileprocessor.services.impl;

import com.ebayexample.urlfileprocessor.errorHandlers.RestTemplateResponseErrorHandler;
import com.ebayexample.urlfileprocessor.logger.LogSingleton;
import com.ebayexample.urlfileprocessor.services.NetworkProcessor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.stream.Collectors.toList;

public class NetworkProcessorImpl implements NetworkProcessor {

    private RestTemplate restTemplate;

    public NetworkProcessorImpl() {
        restTemplate = new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    @Override
    public void makeNetworkRequest(List<URI> uris) {
        for(URI uri: uris) {
            new Thread(() -> {
                ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

                if (response.getStatusCode().equals(HttpStatus.OK)) {
                    LogSingleton.addToSuccessCounter();
                }
            }).start();
        }
    }
}
