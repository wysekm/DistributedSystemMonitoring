package pl.edu.agh.dsm.common.dto;

import org.springframework.hateoas.ResourceSupport;

public class Measurement extends ResourceSupport {
    private String resource;
    private String metric;
    private Double lastValue;

    public Measurement(String resource, String metric, Double lastValue) {
        this.resource = resource;
        this.metric = metric;
        this.lastValue = lastValue;
    }

    public Double getLastValue() {
        return lastValue;
    }

    public void setLastValue(Double lastValue) {
        this.lastValue = lastValue;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
