package pl.edu.agh.dsm.monitor.measurement.impl;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataPredicateFactory;

import java.util.concurrent.TimeUnit;


public class MeasurementDataPredicateFactoryImpl implements MeasurementDataPredicateFactory {

    static final Logger logger = LoggerFactory.getLogger(MeasurementDataPredicateFactoryImpl.class);

    @Override
    public Predicate<MeasurementDataDto> createForData(final DataLimit limit, final int value) {
        logger.debug("create predicate with limit {}, value {}", limit, value);

        if (limit.equals(DataLimit.since)) {
            return new Predicate<MeasurementDataDto>() {

                @Override
                public boolean apply(MeasurementDataDto measurementDataDto) {
                    return (System.currentTimeMillis() - measurementDataDto.getTimestamp()) <= TimeUnit.MILLISECONDS.convert(value, TimeUnit.MINUTES);
                }
            };
        } else if (limit.equals(DataLimit.last)) {
            return new Predicate<MeasurementDataDto>() {
                private int count = value;

                @Override
                public boolean apply(MeasurementDataDto measurementDataDto) {
                    return count-- > 0 ? true : false;
                }
            };
        } else {
            return Predicates.alwaysTrue();
        }
    }
}
