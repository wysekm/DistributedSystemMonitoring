package pl.edu.agh.dsm.front.models;

/**
 * Created by Sakushu on 2014-04-26.
 */
public class ResourceModel {
    private Boolean selected;
    private String resource;
    private String href;
    private String self;
    private String unit;
    private String metric;

    public ResourceModel(Boolean selected, String resource, String href, String self, String unit, String metric){
        this.selected = selected;
        this.resource = resource;
        this.href = href;
        this.self = self;
        this.unit = unit;
        this.metric = metric;
    }

    public ResourceModel(){

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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
