package pl.edu.agh.dsm.datastore;

import java.util.Map;
import java.util.concurrent.Callable;

public abstract class DataStoreAction<T> implements Callable<T> {
    protected Map<String, Map<String, MonitoredResource>> data;

    public void setData(Map<String, Map<String, MonitoredResource>> data) {
        this.data = data;
    }
}
