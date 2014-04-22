package pl.edu.agh.dsm.monitor.measurement.impl;

import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataPredicateFactory;
import pl.edu.agh.dsm.monitor.web.SystemResourceAssemblerSupport;

import java.util.concurrent.TimeUnit;


public class MeasurementDataPredicateFactoryImpl implements MeasurementDataPredicateFactory {

    static final Logger logger = LoggerFactory.getLogger(MeasurementDataPredicateFactoryImpl.class);

    @Override
    public Predicate<MeasurementDataDto> createForData(final DataLimit limit, final int value) {
        logger.debug("create predicate with limit {}, value {}", limit, value);

        return new Predicate<MeasurementDataDto>() {
            @Override
            public boolean apply(MeasurementDataDto measurementDataDto) {
                if(limit.equals(DataLimit.all)){
                    return true;
                } else if(limit.equals(DataLimit.since)){
                    return (System.currentTimeMillis() - measurementDataDto.getTimestamp()) <= TimeUnit.MILLISECONDS.convert(value, TimeUnit.MINUTES);
                } else{
                    //TODO filtr x last

                    return true;
                }
            }
        };
    }
}
