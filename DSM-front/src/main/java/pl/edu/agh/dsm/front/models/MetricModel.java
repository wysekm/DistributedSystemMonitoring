package pl.edu.agh.dsm.front.models;

import java.util.HashMap;

/**
 * Created by Sakushu on 2014-04-26.
 */
public class MetricModel {
    private Boolean selected;
    private String self;
    private String details;
    private String name;
    private String metric;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
