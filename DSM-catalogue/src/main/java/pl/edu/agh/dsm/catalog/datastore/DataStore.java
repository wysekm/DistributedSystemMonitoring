package pl.edu.agh.dsm.catalog.datastore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

//TODO: Replace with in-memory database (SQL: HyperSQL, Sqlite; NoSQL: Redis(K/V))
public class DataStore {

    private static final Logger log = LoggerFactory.getLogger(DataStore.class);

    private static final DataStore INSTANCE = new DataStore();

    private Map<String, MonitorInformation> data = new HashMap<>();

    private ExecutorService es = Executors.newSingleThreadExecutor();

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public <T> T submit(DataStoreAction<T> r) throws DataStoreException {
        r.setData(data);
        Future<T> future = es.submit(r);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException | CancellationException ex) {
            log.error("Exception caught while getting the future value: ", ex);
            throw new DataStoreException("Future.get failed", ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
