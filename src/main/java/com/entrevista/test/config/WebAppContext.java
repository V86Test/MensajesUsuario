package com.entrevista.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring Context
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Configuration
@EnableWebMvc
@Import({DataBaseConfig.class})
@ComponentScan(basePackages = {"com.entrevista.test.resources","com.entrevista.test.services","com.entrevista.test.repository.*"})
public class WebAppContext extends WebMvcConfigurerAdapter {

}
