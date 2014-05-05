package pl.edu.agh.dsm.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Sakushu on 2014-05-05.
 */

@Configuration
@ImportResource("mvc-dispatcher-servlet.xml")
@EnableAutoConfiguration
public class FrontConfig implements WebApplicationInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontConfig.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.addServlet("mvc-dispatcher", new org.springframework.web.servlet.DispatcherServlet());
    }
}
