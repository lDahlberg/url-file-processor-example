package com.ebayexample.urlfileprocessor;

import com.ebayexample.urlfileprocessor.logger.LogSingleton;
import com.ebayexample.urlfileprocessor.readers.FileReader;
import com.ebayexample.urlfileprocessor.services.FileProcessor;
import com.ebayexample.urlfileprocessor.services.impl.FileProcessorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.System.exit;

@SpringBootApplication
public class UrlFileProcessorApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(UrlFileProcessorApplication.class, args);

		List<String> fileNames = Arrays.asList(
				"./inputData/url.0.gz"
		);

		FileProcessor fileProcessor = new FileProcessorImpl();
		fileProcessor.processFiles(fileNames);

		System.out.println("Total successes: " + LogSingleton.getSuccessCounter() + ", Total failures: " + LogSingleton.getFailureCounter());
		exit(0);
	}

}
