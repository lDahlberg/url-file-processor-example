package com.ebayexample.urlfileprocessor.services;

import java.net.URI;
import java.util.List;

public interface NetworkProcessor {

    void makeNetworkRequest(List<URI> uris);
}
