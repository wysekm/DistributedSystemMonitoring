package pl.edu.agh.dsm.catalog.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.dsm.catalog.datastore.*;
import pl.edu.agh.dsm.common.dto.MeasurementInformation;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {
    public List<MeasurementInformation> getAllMeasurements() throws DataStoreException {
        return DataStore.getInstance().submit(new DataStoreAction<List<MeasurementInformation>>() {
            @Override
            public List<MeasurementInformation> call() throws Exception {
                MeasurementInformation m;
                List<MeasurementInformation> ret = new ArrayList<>();
                for (MonitorInformation mi : data.values()) {
                    for (SensorInformation si : mi.getSensors()) {
                        m = new MeasurementInformation();
                        m.add(si.getDetailsLink());
                    }
                }
                if (ret.isEmpty()) {
                    throw new DataStoreException("No results", null, HttpStatus.NO_CONTENT);
                }
                return ret;
            }
        });
    }
}
