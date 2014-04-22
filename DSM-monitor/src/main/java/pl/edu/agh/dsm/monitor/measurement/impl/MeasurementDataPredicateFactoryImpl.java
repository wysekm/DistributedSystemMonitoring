package pl.edu.agh.dsm.monitor.measurement.impl;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataPredicateFactory;
import pl.edu.agh.dsm.monitor.web.SystemResourceAssemblerSupport;

import java.util.concurrent.TimeUnit;


public class MeasurementDataPredicateFactoryImpl implements MeasurementDataPredicateFactory {
    @Override
    public Predicate<MeasurementDataDto> createForData(final DataLimit limit, final int value) {
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
