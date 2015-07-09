package com.entrevista.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Configuration
@Import({DataBaseConfig.class})
@ComponentScan(basePackages = {"com.entrevista.test.resources","com.entrevista.test.services","com.entrevista.test.repository.*"})
public class Context {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
