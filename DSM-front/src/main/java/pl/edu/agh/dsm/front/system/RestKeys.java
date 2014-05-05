package pl.edu.agh.dsm.front.system;

import org.springframework.stereotype.Component;

/**
 * Created by Sakushu on 2014-04-26.
 */
@Component
public class RestKeys {
    private String _embedded;
    private String measurements;
    private String resource;
    private String metric;
    private String _links;
    private String self;
    private String href;
    private String details;

    private String resources;

    public String get_embedded() {
        return _embedded;
    }

    public void set_embedded(String _embedded) {
        this._embedded = _embedded;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String get_links() {
        return _links;
    }

    public void set_links(String _links) {
        this._links = _links;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
