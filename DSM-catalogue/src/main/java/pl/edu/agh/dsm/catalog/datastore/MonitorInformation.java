package pl.edu.agh.dsm.catalog.datastore;

import org.springframework.hateoas.Link;

import java.util.List;

public class MonitorInformation {
    private List<SensorInformation> sensors;
    private Link measurementCollectionLink;

    public MonitorInformation(List<SensorInformation> sensors, Link measurementCollectionLink) {
        this.sensors = sensors;
        this.measurementCollectionLink = measurementCollectionLink;
    }

    public Link getMeasurementCollectionLink() {
        return measurementCollectionLink;
    }

    public void setMeasurementCollectionLink(Link measurementCollectionLink) {
        this.measurementCollectionLink = measurementCollectionLink;
    }

    public List<SensorInformation> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorInformation> sensors) {
        this.sensors = sensors;
    }

}
