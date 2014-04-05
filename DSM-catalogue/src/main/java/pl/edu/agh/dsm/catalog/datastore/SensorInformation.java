package pl.edu.agh.dsm.catalog.datastore;

import org.springframework.hateoas.Link;

public class SensorInformation {
    private String nodeId;
    private String measurementId;
    private String metric;
    private Link detailsLink;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Link getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(Link detailsLink) {
        this.detailsLink = detailsLink;
    }
}
