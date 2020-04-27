package com.ebayexample.urlfileprocessor.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FileProcessor {
    void processFiles(List<String> fileNames) throws InterruptedException, ExecutionException;
}
