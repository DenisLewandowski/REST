package pl.coreservices.service;

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
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import pl.coreservices.utils.FileProcessor;
import pl.coreservices.utils.LastModifiedFileFilter;

import java.io.File;

@Service
public class FileProcessorService {
    private static final String DIRECTORY = "C:\\listen";

    @Bean
    public IntegrationFlow processFileFlow() {
        return IntegrationFlows
                .from("fileInputChannel")
                .transform(fileToStringTransformer())
                .handle("fileProcessor", "process").get();
    }

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        CompositeFileListFilter<File> filters =new CompositeFileListFilter<>();
        filters.addFilter(new LastModifiedFileFilter());

        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setAutoCreateDirectory(true);
        source.setDirectory(new File(DIRECTORY));
        source.setFilter(filters);

        return source;
    }

    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }

    @Bean
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }

}
