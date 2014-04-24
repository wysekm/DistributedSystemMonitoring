package pl.edu.agh.dsm.monitor.dto;

import java.util.UUID;


public class SimpleMeasurementDataDto {
    UUID id;
    String resource;
    String metric;
    long timestamp;
    long value;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleMeasurementDataDto)) return false;

        SimpleMeasurementDataDto that = (SimpleMeasurementDataDto) o;

        if (timestamp != that.timestamp) return false;
        if (value != that.value) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (metric != null ? !metric.equals(that.metric) : that.metric != null) return false;
        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (metric != null ? metric.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (int) (value ^ (value >>> 32));
        return result;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
