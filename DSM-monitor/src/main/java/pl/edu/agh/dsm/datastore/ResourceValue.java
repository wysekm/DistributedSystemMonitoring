package pl.edu.agh.dsm.datastore;

import java.util.Date;

public class ResourceValue {
    private Double value;
    private Date timestamp;

    public ResourceValue(Double value, Date timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
