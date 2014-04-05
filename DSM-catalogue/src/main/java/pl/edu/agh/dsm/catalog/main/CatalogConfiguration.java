package pl.edu.agh.dsm.catalog.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class CatalogConfiguration {
    private static final Logger log = LoggerFactory.getLogger(CatalogConfiguration.class);

    private static final String PROPERTIES_FILE = "catalog.properties";
    private static final Integer DEFAULT_PORT = 9998;
    private static final String DEFAULT_CONTEXT = "/dsm/catalog";
    private static Integer port;
    private static String rootContext;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE));
            port = Integer.parseInt(properties.getProperty("port", DEFAULT_PORT.toString()));
            rootContext = properties.getProperty("context", DEFAULT_CONTEXT);
        } catch (IOException | NumberFormatException ex) {
            log.warn("Unable to read properties from file '" + PROPERTIES_FILE + "': ", ex);
            log.info("Using default port (" + DEFAULT_PORT + ") and root context (" + DEFAULT_CONTEXT + ")");
            port = DEFAULT_PORT;
            rootContext = DEFAULT_CONTEXT;
        }
    }

    public static Integer getPort() {
        return port;
    }

    public static String getRootContext() {
        return rootContext;
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        AbstractEmbeddedServletContainerFactory factory;
        //factory = new JettyEmbeddedServletContainerFactory();
        factory = new TomcatEmbeddedServletContainerFactory();

        factory.setContextPath(rootContext);
        factory.setPort(port);

        return factory;
    }
}

