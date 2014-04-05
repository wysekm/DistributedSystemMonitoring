package pl.edu.agh.dsm.main;

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
public class MonitorConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MonitorConfiguration.class);

    private static final String PROPERTIES_FILE = "monitor.properties";
    private static final Integer DEFAULT_HTTP_PORT = 9998;
    private static final Integer DEFAULT_UDP_PORT = 1138;
    private static final Integer DEFAULT_HISTORY_SIZE = 500;
    private static Integer httpPort;
    private static Integer udpPort;


    private static Integer historySize;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE));
            httpPort = Integer.parseInt(properties.getProperty("httpPort", DEFAULT_HTTP_PORT.toString()));
            udpPort = Integer.parseInt(properties.getProperty("udpPort", DEFAULT_UDP_PORT.toString()));
            historySize = Integer.parseInt(properties.getProperty("historySize", DEFAULT_HISTORY_SIZE.toString()));
        } catch (IOException | NumberFormatException ex) {
            log.warn("Unable to read all properties from file '" + PROPERTIES_FILE + "': ", ex);
            log.info("Using default udpPort (" + DEFAULT_UDP_PORT + "), httpPort (" + DEFAULT_HTTP_PORT +
                    ") and history size ( " + DEFAULT_HISTORY_SIZE + ") ");
            httpPort = DEFAULT_HTTP_PORT;
            udpPort = DEFAULT_UDP_PORT;
            historySize = DEFAULT_HISTORY_SIZE;
        }
    }

    public static Integer getHttpPort() {
        return httpPort;
    }

    public static int getUdpPort() {
        return udpPort;
    }

    public static Integer getHistorySize() {
        return historySize;
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        AbstractEmbeddedServletContainerFactory factory;
        //factory = new JettyEmbeddedServletContainerFactory();
        factory = new TomcatEmbeddedServletContainerFactory("", httpPort);
        return factory;
    }
}

