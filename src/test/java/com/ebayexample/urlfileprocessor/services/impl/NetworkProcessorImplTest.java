package com.ebayexample.urlfileprocessor.services.impl;

import com.ebayexample.urlfileprocessor.services.CounterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NetworkProcessorImplTest {

    @InjectMocks
    private NetworkProcessorImpl subject;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void makeNetworkRequest() {
        List<String> urls = Arrays.asList("https://www.ebay.com", "http://www.ebay.com");

        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok().build());

        subject.makeNetworkRequest(urls);

        int expectedSuccesses = 2;

        assertEquals(expectedSuccesses, CounterService.getSuccessCounter());
    }
}