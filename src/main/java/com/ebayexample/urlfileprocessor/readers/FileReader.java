package com.ebayexample.urlfileprocessor.readers;

import com.ebayexample.urlfileprocessor.services.NetworkProcessor;
import com.ebayexample.urlfileprocessor.services.impl.NetworkProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FileReader {

    private NetworkProcessor networkProcessor;
    private String fileToRead;

    public FileReader(String fileToRead) {
        networkProcessor = new NetworkProcessorImpl();
        this.fileToRead = fileToRead;
    }

    public void readFile() {
        try (InputStream in = new GZIPInputStream(new FileInputStream(this.fileToRead))) {

            Reader decoder = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(decoder);

            List<String> uris = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                uris.add(line);

                if(uris.size() == 250) {
                    networkProcessor.makeNetworkRequest(uris);
                    uris = new ArrayList<>();
                }
            }
            networkProcessor.makeNetworkRequest(uris);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
