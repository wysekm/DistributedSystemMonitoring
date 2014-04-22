package pl.edu.agh.dsm.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SensorConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SensorConfiguration.class);

    private String resource;

    public SensorConfiguration() {
        log.trace("Creating SensorConfiguration instance.");
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
