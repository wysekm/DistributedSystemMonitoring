package pl.edu.agh.dsm.catalog.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.management.monitor.Monitor;

@ComponentScan(basePackages = { "dsm.catalog" })
@EnableAutoConfiguration
public class Catalog {

    private static final Logger log = LoggerFactory.getLogger(Monitor.class);


    public static void main(String[] args) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        log.info("Starting HTTP server on port "
                + CatalogConfiguration.getPort() + " using root context: '"
                + CatalogConfiguration.getRootContext() + "'");

        SpringApplication.run(Monitor.class, args);
    }
}
