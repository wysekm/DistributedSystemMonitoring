package pl.edu.agh.dsm.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"pl.edu.agh.dsm"})
@EnableAutoConfiguration
public class Monitor {

    private static final Logger log = LoggerFactory.getLogger(Monitor.class);


    public static void main(String[] args) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        log.info("Starting UDP sensor listener on port "
                + MonitorConfiguration.getUdpPort());

        log.info("Starting HTTP server on port "
                + MonitorConfiguration.getHttpPort());

        // TODO: clean shutdown

        SpringApplication.run(Monitor.class, args);
    }
}
