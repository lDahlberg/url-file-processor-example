package com.ebayexample.urlfileprocessor.services.impl;

import com.ebayexample.urlfileprocessor.readers.FileReader;
import com.ebayexample.urlfileprocessor.services.FileProcessor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FileProcessorImpl implements FileProcessor {

    @Override
    public void processFiles(List<String> fileNames) throws InterruptedException {
        int expectedCoresPlusOne = 5;
        ExecutorService executor = Executors.newFixedThreadPool(expectedCoresPlusOne);

        List<Callable<Void>> callables = fileNames.stream().map(
                fileName -> (Callable<Void>) () -> {
            FileReader reader = new FileReader(fileName);
            reader.readFile();
            // Concession to invokeAll not being overloaded for Runnable
            return null;
        }).collect(Collectors.toList());

        // Does not return anything currently
        executor.invokeAll(callables);
    }
}
