package pl.coreservices.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileProcessor {

    private static final String HEADER_FILE_NAME = "file_name";
    private static final String MSG = "%s received. Content: %s";

    public void process(Message<String> msg) {
        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);
        String content = msg.getPayload();

        System.out.println(fileName);
    }


}