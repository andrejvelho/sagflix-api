package br.com.resultatec.sagflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class SagflixApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagflixApiApplication.class, args);
	}

	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/messagens");
        messageSource.setDefaultEncoding("UFT-8");
        messageSource.setCacheSeconds(1);

        return messageSource;
    }

}
