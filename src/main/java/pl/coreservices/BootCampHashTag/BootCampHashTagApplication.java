package pl.coreservices.BootCampHashTag;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
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
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.utils.FileProcessor;
import pl.coreservices.utils.LastModifiedFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {


	public static void main(String[] args) {

		SpringApplication.run(BootCampHashTagApplication.class, args);
	}


}
