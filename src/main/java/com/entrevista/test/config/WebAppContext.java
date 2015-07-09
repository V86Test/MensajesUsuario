package com.entrevista.test.config;

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
@Import({Context.class})
public class WebAppContext extends WebMvcConfigurerAdapter {

}
