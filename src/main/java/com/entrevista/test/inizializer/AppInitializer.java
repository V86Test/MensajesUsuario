package com.entrevista.test.inizializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Load the Servlet context
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public class AppInitializer  implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext ctx = getContext();
        servletContext.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(ctx));           
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/*");
    }
    
     private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.entrevista.test");
        return context;
    }

}
