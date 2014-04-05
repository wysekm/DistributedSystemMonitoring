package pl.edu.agh.dsm.catalog.datastore;

import java.util.Map;
import java.util.concurrent.Callable;

public abstract class DataStoreAction<T> implements Callable<T> {
    protected Map<String, MonitorInformation> data;

    public void setData(Map<String, MonitorInformation> data) {
        this.data = data;
    }
}
