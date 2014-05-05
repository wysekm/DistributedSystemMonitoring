package pl.edu.agh.dsm.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Sakushu on 2014-05-05.
 */

@ImportResource("mvc-dispatcher-servlet.xml")
@EnableAutoConfiguration
public class FrontConfig {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontConfig.class, args);
    }
}
