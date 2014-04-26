package pl.edu.agh.dsm.common.measurements;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;
import pl.edu.agh.dsm.common.measurement.impl.MeasurementRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeasurementRepositoryTest {

    static MeasurementRepository measurementRepository = new MeasurementRepositoryImpl();
    static MeasurementDto measurementDto;

    @Test
    public void testAdd() throws IOException {
        UUID uuid = UUID.randomUUID();
        measurementDto = new MeasurementDto(uuid, "Host", "memUsage", "mb", "http://localhost:8085");
        measurementRepository.save(measurementDto);
        Assert.assertEquals(measurementDto, measurementRepository.find(uuid));
    }

    @Test
    public void testDelete() throws IOException {
        Predicate<MeasurementDto> precondition = Predicates.alwaysTrue();
        List<MeasurementDto> listOfMeasurements = measurementRepository.findAll(precondition);

        Assert.assertTrue(listOfMeasurements.contains(measurementDto));

        measurementRepository.remove(measurementDto.getId());
        listOfMeasurements = measurementRepository.findAll(precondition);
        Assert.assertFalse(listOfMeasurements.contains(measurementDto));
    }

    @Test
    public void testGetList() throws IOException {
        Predicate<MeasurementDto> precondition = Predicates.alwaysTrue();
        List<MeasurementDto> listOfMeasurements = measurementRepository.findAll(precondition);

        Assert.assertTrue(listOfMeasurements.isEmpty());
    }


}
