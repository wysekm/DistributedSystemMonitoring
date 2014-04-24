package pl.edu.agh.dsm.common.measurement.impl;

import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementPredicateFactory;

public class MeasurementPredicateFactoryImpl implements MeasurementPredicateFactory {

    static final Logger logger = LoggerFactory.getLogger(MeasurementPredicateFactoryImpl.class);

    @Override
    public Predicate<MeasurementDto> createForMeasurement(final String metric, final String resource) {
        logger.debug("create new measurement predicate with metric " + metric + " and resource " + resource);

        return new Predicate<MeasurementDto>() {
            @Override
            public boolean apply(MeasurementDto measurementDto) {
                if (metric != null && resource != null) {
                    return measurementDto.getMetric().equals(metric) && measurementDto.getResource().equals(resource);
                } else if (metric != null) {
                    return measurementDto.getMetric().equals(metric);
                } else if (resource != null) {
                    return measurementDto.getResource().equals(resource);
                }
                return false;
            }
        };
    }
}
