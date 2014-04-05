package pl.edu.agh.dsm.datastore;

import pl.edu.agh.dsm.main.MonitorConfiguration;

import java.util.Date;
import java.util.List;

public class MonitoredResource {
    private String nodeId;
    private String sensorId;
    private CircularBuffer<ResourceValue> values;
    private Long lastUpdate;
    private String origin;

    public MonitoredResource(String nodeId, String sensorId) {
        this.nodeId = nodeId;
        this.sensorId = sensorId;
        this.values = new CircularBuffer<>(MonitorConfiguration.getHistorySize());
        this.lastUpdate = 0L;
    }


    public String getNodeId() {
        return nodeId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public List<ResourceValue> getValues() {
        return values;
    }

    public void addValue(Date timestamp, Double value) {
        values.add(new ResourceValue(value, timestamp));
        lastUpdate = System.currentTimeMillis();
    }

    public ResourceValue getLast() {
        return values.getLast();
    }

    public boolean isValid() {
        long invalidationPeriod = 25000; // TODO: externalize
        return System.currentTimeMillis() - lastUpdate > invalidationPeriod;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
