package pl.edu.agh.dsm.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Sakushu on 2014-05-05.
 */

@Configuration
@ImportResource("mvc-dispatcher-servlet.xml")
@EnableAutoConfiguration
public class FrontConfig {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontConfig.class, args);
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
