package com.ebayexample.urlfileprocessor.services.impl;

import com.ebayexample.urlfileprocessor.readers.FileReader;
import com.ebayexample.urlfileprocessor.services.FileProcessor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// This class left without unit tests as it has no logic
// And unit testing it within a multi-threaded process would be
// more trouble than it's worth
public class FileProcessorImpl implements FileProcessor {

    @Override
    public void processFiles(List<String> fileNames) {
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
        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
