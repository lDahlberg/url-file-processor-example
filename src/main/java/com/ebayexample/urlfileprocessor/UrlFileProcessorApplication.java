package com.ebayexample.urlfileprocessor;

import com.ebayexample.urlfileprocessor.constants.Constants;
import com.ebayexample.urlfileprocessor.logger.Logger;
import com.ebayexample.urlfileprocessor.services.FileProcessor;
import com.ebayexample.urlfileprocessor.services.impl.FileProcessorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlFileProcessorApplication {

	public static void main(String[] args)  {
		SpringApplication.run(UrlFileProcessorApplication.class, args);

		Logger logger = new Logger();
		logger.setLogger();

		FileProcessor fileProcessor = new FileProcessorImpl();
		fileProcessor.processFiles(Constants.FILES);

	}

}
