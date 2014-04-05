package pl.edu.agh.dsm.service;

import pl.edu.agh.dsm.common.dto.Measurement;
import pl.edu.agh.dsm.common.dto.UdpSensorUpdate;
import pl.edu.agh.dsm.datastore.*;
import pl.edu.agh.dsm.listener.SensorListener;
import pl.edu.agh.dsm.main.MonitorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

@Service
public class DataService {

    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    @PostConstruct
    private void init() {
        log.info("DataService constructed, creating and starting SensorListener");
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new SensorListener(MonitorConfiguration.getUdpPort(), this));
    }

    public void registerNewValue(final UdpSensorUpdate udpSensorUpdate, final String origin) throws DataStoreException {
        DataStore.getInstance().submit(new DataStoreAction<Object>() {
            @Override
            public Object call() {
                log.trace("Registering new value for " + udpSensorUpdate.getNodeId() + "-"
                        + udpSensorUpdate.getId() + " of type " + udpSensorUpdate.getMetric());
                MonitoredResource found = null;

                Map<String, MonitoredResource> sensorsDataForNode = data.get(udpSensorUpdate.getNodeId());
                if (sensorsDataForNode != null) {
                    found = sensorsDataForNode.get(udpSensorUpdate.getId());
                }

                if (found == null) {
                    found = new MonitoredResource(udpSensorUpdate.getNodeId(), udpSensorUpdate.getId());
                    found.setOrigin(origin);
                    if (sensorsDataForNode == null) {
                        sensorsDataForNode = new HashMap<>();
                        data.put(udpSensorUpdate.getNodeId(), sensorsDataForNode);
                    }
                    sensorsDataForNode.put(udpSensorUpdate.getId(), found);
                }
                if (found.getOrigin().equals(origin)) {
                    found.addValue(udpSensorUpdate.getTimestamp(), udpSensorUpdate.getValue());
                } else {
                    log.error("Sensor update for " + udpSensorUpdate.getNodeId() + "-" + udpSensorUpdate.getId()
                            + " from wrong origin (current: " + origin + ", previous: " + found.getOrigin() + ")."
                            + " Ignoring this update, please check your sensor configuration for non-unique node ids.");
                }
                return null;
            }
        });
    }

    public Measurement getLastValueForSensor(final String resource, final String metric) throws DataStoreException {
        return DataStore.getInstance().submit(new DataStoreAction<Measurement>() {
            @Override
            public Measurement call() throws DataStoreException {
                if (data.get(resource).get(metric).isValid()) {
                    ResourceValue last = data.get(resource).get(metric).getLast();
                    if (last != null) {
                        return new Measurement(resource, metric, last.getValue());
                    }
                }
                throw new DataStoreException("No results", null, HttpStatus.NOT_FOUND);
            }
        });
    }

    public List<Measurement> getAllMeasurements() throws DataStoreException {
        return DataStore.getInstance().submit(new DataStoreAction<List<Measurement>>() {
            @Override
            public List<Measurement> call() throws Exception {
                Measurement m;
                List<Measurement> ret = new ArrayList<>();
                for (String resource : data.keySet()) {
                    for (String metric : data.get(resource).keySet()) {
                        if (data.get(resource).get(metric).isValid()) {
                            m = new Measurement(resource, metric, data.get(resource).get(metric).getLast().getValue());
                            ret.add(m);
                        }
                    }
                }
                if (ret.isEmpty()) {
                    throw new DataStoreException("No results", null, HttpStatus.NO_CONTENT);
                }
                return ret;
            }
        });
    }

    public List<Measurement> getAllValuesForSensor(final String tgtResource, final String tgtMetric) throws DataStoreException {
        return DataStore.getInstance().submit(new DataStoreAction<List<Measurement>>() {
            @Override
            public List<Measurement> call() throws Exception {
                Measurement m;
                List<Measurement> ret = new ArrayList<>();
                for (String resource : data.keySet()) {
                    if (resource.equals(tgtResource)) {
                        for (String metric : data.get(resource).keySet()) {
                            if (metric.equals(tgtMetric)) {
                                if (data.get(resource).get(metric).isValid()) {
                                    for (ResourceValue v : data.get(resource).get(metric).getValues()) {
                                        m = new Measurement(resource, metric, v.getValue());
                                        ret.add(m);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                if (ret.isEmpty()) {
                    throw new DataStoreException("No results", null, HttpStatus.NOT_FOUND);
                }
                return ret;
            }
        });
    }
}
